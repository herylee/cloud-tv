package joshua.cloudtv.service.impl;

import joshua.cloudtv.constant.PublisherCondition;
import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.constant.UserPermission;
import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.dao.model.Publisher;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.repository.LiveRoomRepository;
import joshua.cloudtv.repository.PublisherRepository;
import joshua.cloudtv.repository.UserRepository;
import joshua.cloudtv.service.AdminService;
import joshua.cloudtv.vo.request.ModifyConditionRequest;
import joshua.cloudtv.vo.request.ValidatePublisherRequest;
import joshua.cloudtv.vo.response.ModifyConditionResponse;
import joshua.cloudtv.vo.response.ValidatePublisherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @see AdminService
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    //FIXME TX
    public ValidatePublisherResponse validatePublisher(ValidatePublisherRequest validatePublisherRequest) {
        ValidatePublisherResponse response = new ValidatePublisherResponse();

        // 管理员给用户提升为主播，相对应地，为其修改用户权限、主播状态和新建房间
        int userId = validatePublisherRequest.getUserId();

        User user = userRepository.getUserById(userId);
        Publisher publisher = publisherRepository.getPublisherById(userId);

        // 判断用户已经是主播
        if( UserPermission.isPublisher(user.getPermission())
                || publisher.getPublisherCondition() == PublisherCondition.NORMAL.getCondition()
                || publisher.getRoomId() != null ) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("该用户已经为主播！");
            return response;
        }

        //为用户设置主播的权限
        user.setPermission(user.getPermission() + UserPermission.PUBLISHER.getPermission());
        //为主播修改状态
        publisher.setPublisherCondition(PublisherCondition.NORMAL.getCondition());
        // 为主播新建房间
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setPublisherId(publisher.getId());
        liveRoom.setRoomName(user.getNickname()+"的房间"); // 创建初始化的房间名

        int roomId = liveRoomRepository.addLiveRoomAndGetId(liveRoom);
        liveRoom.setPosterUrl("/liveRoom/"+roomId+".jpg");
        liveRoomRepository.updateLiveRoom(liveRoom);

        // 记录房间id
        publisher.setRoomId(roomId);
        publisherRepository.updatePublisher(publisher);
        userRepository.updateUserPermission(user);

        // TODO
        // 提醒用户的主播身份已经被验证，如发送邮件等等

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("用户"+user.getNickname()+"("+user.getId()+")的身份已经被验证，现在已经提升为主播身份！");
        return response;
    }

    public ModifyConditionResponse modifyCondition(ModifyConditionRequest modifyConditionRequest) {
        ModifyConditionResponse response = new ModifyConditionResponse();

        Publisher publisher = publisherRepository.getPublisherById(modifyConditionRequest.getPublisherId());
        if(publisher==null) {
            response.setRetCode(RetCode.NOT_FOUND.retCode);
            response.setMessage("不存在该主播！");
        }
        publisher.setPublisherCondition( modifyConditionRequest.getPublisherCondition());

        publisherRepository.updatePublisher(publisher);
        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("主播状态已改变！");
        return response;
    }
}
