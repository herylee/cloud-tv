package joshua.cloudtv.service.impl;

import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.constant.UserPermission;
import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.dao.model.Publisher;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.repository.LiveRoomRepository;
import joshua.cloudtv.repository.PublisherRepository;
import joshua.cloudtv.repository.UserRepository;
import joshua.cloudtv.service.PublisherService;
import joshua.cloudtv.vo.request.UpdateLiveRoomInfosRequest;
import joshua.cloudtv.vo.response.UpdateLiveRoomInfosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @see PublisherService
 */
@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    public UpdateLiveRoomInfosResponse updateLiveRoomInfos(UpdateLiveRoomInfosRequest updateLiveRoomInfosRequest) {
        UpdateLiveRoomInfosResponse response = new UpdateLiveRoomInfosResponse();
        User user = userRepository.getUserByUUID(updateLiveRoomInfosRequest.getUuid());

        // uuid不正确
        if(user == null) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("用户未登录！");
            return response;
        }

        // 该用户不是主播
        if( ! UserPermission.PUBLISHER.isPublisher(user.getPermission())) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("您不是主播！");
        }

        // 主播的状态只能控制主播能否推送直播，更改房间信息是允许的
        Publisher publisher = publisherRepository.getPublisherById(user.getId());
        LiveRoom liveRoom = liveRoomRepository.getLiveRoomById(publisher.getRoomId());

        String roomName = updateLiveRoomInfosRequest.getRoomName();
        String roomDescription = updateLiveRoomInfosRequest.getRoomDescription();
        String posterUrl = updateLiveRoomInfosRequest.getPosterUrl();
        int publishType = updateLiveRoomInfosRequest.getPublishType();

        if(roomName!=null) liveRoom.setRoomName(roomName);
        if(roomDescription!=null) liveRoom.setRoomDescription(roomDescription);
        if(posterUrl!=null) liveRoom.setPosterUrl(posterUrl);
        if(publishType!=0) liveRoom.setPublishType(publishType);
        liveRoomRepository.updateLiveRoom(liveRoom);

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("直播房间更新成功！");
        return response;
    }

    @Override
    public LiveRoom getLiveRoomByPublisherId(int publisherId) {
        Publisher publisher = publisherRepository.getPublisherById(publisherId);
        LiveRoom liveRoom = liveRoomRepository.getLiveRoomById(publisher.getRoomId());
        return liveRoom;
    }

}
