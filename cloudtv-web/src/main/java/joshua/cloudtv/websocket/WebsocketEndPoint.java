package joshua.cloudtv.websocket;

import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by JoshuaShaw on 2016/12/26.
 */
public class WebsocketEndPoint extends TextWebSocketHandler {

    // 静态内部类
    static class RoomUser {
        public String getRoomId() {
            return roomId;
        }
        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }
        private String roomId;
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
        private User user;
        public RoomUser(User user, String roomId) {this.user = user;this.roomId=roomId;}
    }

    @Autowired
    private UserService userService;
    // 记录房间和session的联系（频道）
    private static Map<String, Set<WebSocketSession>> roomMap = new HashMap<>();
    // 记录用户（可以发送信息的用户）
    private static Map<String, RoomUser> userMap = new HashMap<>();

    // 将session加入到相应的channel
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //HttpHeaders httpHeaders= session.getHandshakeHeaders();
        Map<String, String> params = splitQuery(session.getUri());
        String uuid = params.get("uuid");
        String roomId = params.get("roomId");// 必须的

        // 登录的用户
        if(uuid!=null) {
            User user = userService.getSessionUser(uuid);
            if (user!=null) {
                RoomUser roomUser = new RoomUser(user, roomId);
                userMap.put(session.getId(), roomUser);
            }
        }

        if(roomMap.containsKey(roomId))
            roomMap.get(roomId).add(session);// 将session加进去
        else {
            Set<WebSocketSession> channel = new HashSet<>();
            channel.add(session);
            roomMap.put(roomId, channel);
        }
    }

    // 发送text
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        RoomUser roomUser = userMap.get(session.getId());
        // 不登录的用户忽略
        if(roomUser==null) {
            System.out.println("unauth user!");
            return;
        }

        TextMessage returnMessage = new TextMessage(roomUser.getUser().getNickname()+": "+message.getPayload());
        Set<WebSocketSession> channel = roomMap.get(roomUser.getRoomId());
        for (WebSocketSession s : channel) {
            s.sendMessage(returnMessage);
        }
    }

    // 将session移除
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        boolean isEmpty = false;
        String roomId = null;
        for(Map.Entry<String, Set<WebSocketSession>> e:roomMap.entrySet()) {
            if(e.getValue().contains(session)) {
                e.getValue().remove(session);
                if(e.getValue().size()==0) {
                    isEmpty = true;
                    roomId = e.getKey();
                }
                break;
            }
        }
        if(isEmpty) roomMap.remove(roomId);
    }

    // print exception
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        exception.printStackTrace();
    }

    public static Map<String, String> splitQuery(URI uri) {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = uri.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            try {
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return query_pairs;
    }
}
