package joshua.cloudtv.controller;

import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.dao.model.Publisher;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.service.PublisherService;
import joshua.cloudtv.service.QueryService;
import joshua.cloudtv.service.UserService;
import joshua.cloudtv.utils.JsonKit;
import joshua.cloudtv.vo.request.UpdateLiveRoomInfosRequest;
import joshua.cloudtv.vo.request.UpdateUserInfosRequest;
import joshua.cloudtv.vo.response.UpdateLiveRoomInfosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by JoshuaShaw on 2016/12/19.
 */

@Controller
@RequestMapping(value = "/liveRoom")
public class LiveRoomController {
    @Autowired
    private QueryService queryService;

    @Autowired
    private UserService userService;

    // 直播房间的界面
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String liveRoom(@PathVariable("id") int id,HttpSession httpSession, Model model) {
        LiveRoom liveRoom = queryService.getLiveRoomById(id);
        User publisher = userService.getUserById( liveRoom.getPublisherId() );
        model.addAttribute("liveRoom", liveRoom);
        model.addAttribute("publisher", publisher);
        //TODO
        String wsUri = new StringBuilder("ws://cloud.tv:8080/ws?roomId=").append(liveRoom.getId())
                .append("&uuid=").append((String) httpSession.getAttribute("uuid")).toString();
        model.addAttribute("wsUri", wsUri);
        return "liveRoom";
    }
}
