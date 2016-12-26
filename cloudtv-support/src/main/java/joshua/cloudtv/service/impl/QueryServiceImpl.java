package joshua.cloudtv.service.impl;

import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.repository.LiveRoomRepository;
import joshua.cloudtv.repository.PublisherRepository;
import joshua.cloudtv.repository.UserRepository;
import joshua.cloudtv.service.QueryService;
import joshua.cloudtv.vo.request.GetLiveRoomsRequest;
import joshua.cloudtv.vo.response.GetLiveRoomsResponse;
import joshua.cloudtv.vo.view.LiveRoomView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @see QueryService
 */
@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    public GetLiveRoomsResponse getLiveRooms(GetLiveRoomsRequest getLiveRoomsRequest) {
        int publishType = getLiveRoomsRequest.getPublishTypeId();
        int currentPage = getLiveRoomsRequest.getCurrentPage();
        int pageRank = getLiveRoomsRequest.getPageRank();
        List<LiveRoom> liveRoomList = liveRoomRepository.getLiveRooms(publishType, currentPage, pageRank);

        List<LiveRoomView> liveRoomViewList = new ArrayList<>();

        // 组装liveRoom 到 liveRoomView
        for (LiveRoom liveRoom:liveRoomList) {
            LiveRoomView e = new LiveRoomView();
            e.setPublisherId(liveRoom.getUser().getId());
            e.setPublisherNickName(liveRoom.getUser().getNickname());
            e.setRoomId(liveRoom.getId());
            e.setRoomName(liveRoom.getRoomName());
            e.setLiveWatchCount(new Random().nextInt(100));
            e.setPosterUrl(liveRoom.getPosterUrl());
            liveRoomViewList.add(e);
        }

        GetLiveRoomsResponse response = new GetLiveRoomsResponse();
        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("查询成功");
        response.setLiveRoomsList(liveRoomViewList);

        return response;
    }

    public LiveRoom getLiveRoomById(int roomId) {
        return liveRoomRepository.getLiveRoomById(roomId);
    }
}
