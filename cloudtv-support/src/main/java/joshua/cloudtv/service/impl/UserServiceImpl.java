package joshua.cloudtv.service.impl;

import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.dao.model.UserSecurity;
import joshua.cloudtv.repository.LiveRoomRepository;
import joshua.cloudtv.repository.PublisherRepository;
import joshua.cloudtv.repository.UserRepository;
import joshua.cloudtv.service.UserService;
import joshua.cloudtv.utils.DateUtil;
import joshua.cloudtv.vo.request.FollowPublisherRequest;
import joshua.cloudtv.vo.request.LostAndFoundRequest;
import joshua.cloudtv.vo.request.SetSecurityQuestionRequest;
import joshua.cloudtv.vo.request.UpdateUserInfosRequest;
import joshua.cloudtv.vo.response.FollowPublisherResponse;
import joshua.cloudtv.vo.response.LostAndFoundResponse;
import joshua.cloudtv.vo.response.SetSecurityQuestionResponse;
import joshua.cloudtv.vo.response.UpdateUserInfosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    public User getSessionUser(String uuid) {
        return userRepository.getUserByUUID(uuid);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    public UpdateUserInfosResponse updateUserInfos(UpdateUserInfosRequest updateUserInfosRequest) {
        UpdateUserInfosResponse response = new UpdateUserInfosResponse();
        User user = userRepository.getUserByUUID(updateUserInfosRequest.getUuid());

        // uuid不正确
        if(user == null) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("用户未登录！");
            return response;
        }

        String nickName = updateUserInfosRequest.getNickName();
        String phoneNumber = updateUserInfosRequest.getPhoneNumber();
        String headUrl = updateUserInfosRequest.getHeadUrl();
        String selfSignature = updateUserInfosRequest.getSelfSignature();
        String bornTime = updateUserInfosRequest.getBornTime();
        String province = updateUserInfosRequest.getProvince();
        String city = updateUserInfosRequest.getCity();
        String area = updateUserInfosRequest.getArea();
        String occupation = updateUserInfosRequest.getOccupation();

        if(!isNullOrEmpty(nickName)) user.setNickname(nickName);
        if(!isNullOrEmpty(phoneNumber)) user.setPhoneNumber(phoneNumber);
        if(!isNullOrEmpty(headUrl)) user.setHeadUrl(headUrl);
        if(!isNullOrEmpty(selfSignature)) user.setSelfSignature(selfSignature);
        if(!isNullOrEmpty(bornTime)) user.setBornTime(DateUtil.stringToDate(bornTime));
        if(!isNullOrEmpty(province)) user.setProvince(province);
        if(!isNullOrEmpty(city)) user.setCity(city);
        if(!isNullOrEmpty(area)) user.setArea(area);
        if(!isNullOrEmpty(occupation)) user.setOccupation(occupation);

        userRepository.updateUser(user);

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("用户信息更新成功！");
        return response;
    }

    public SetSecurityQuestionResponse setSecurityQuestion(SetSecurityQuestionRequest setSecurityQuestionRequest) {
        SetSecurityQuestionResponse response = new SetSecurityQuestionResponse();

        User user = userRepository.getUserByUUID(setSecurityQuestionRequest.getUuid());
        // uuid不正确
        if(user == null) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("用户未登录！");
            return response;
        }

        UserSecurity userSecurity = userRepository.getUserSecurityById(user.getId());
        // 第一次设置，因此不存在userSecurity实例
        if(userSecurity != null) {
            response.setRetCode(RetCode.SERVER_ERROR.retCode);
            response.setMessage("服务器错误！");
            return response;
        }

        userSecurity = new UserSecurity();
        userSecurity.setQuestionOne(setSecurityQuestionRequest.getQuestionOne());
        userSecurity.setQuestionTwo(setSecurityQuestionRequest.getQuestionTwo());
        userSecurity.setQuestionThree(setSecurityQuestionRequest.getQuestionThree());
        userSecurity.setAnswerOne(setSecurityQuestionRequest.getAnswerOne());
        userSecurity.setAnswerTwo(setSecurityQuestionRequest.getAnswerTwo());
        userSecurity.setQuestionThree(setSecurityQuestionRequest.getQuestionThree());
        userRepository.addUserSecurity(userSecurity);

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("安全密码设置成功！");
        return response;
    }

    public LostAndFoundResponse findPassword(LostAndFoundRequest lostAndFoundRequest) {
        LostAndFoundResponse response = new LostAndFoundResponse();
        // 获得email对应的user实例
        User user = userRepository.getUserByEmail(lostAndFoundRequest.getEmail());

        //Email未被注册
        if(user == null) {
            response.setRetCode(RetCode.SUCCESS.retCode);
            response.setMessage("Email不正确！");
            return response;
        }

        // 用户注册后必须引导其到设置安全问题的页面
        UserSecurity userSecurity = userRepository.getUserSecurityById(user.getId());
        if(userSecurity == null) {
            response.setRetCode(RetCode.SERVER_ERROR.retCode);
            response.setMessage("服务器有误！");
        }

        // 验证成功
        if(userSecurity.getAnswerOne().equals(lostAndFoundRequest.getAnswerOne()) &&
                userSecurity.getAnswerTwo().equals(lostAndFoundRequest.getAnswerTwo()) &&
                userSecurity.getAnswerThree().equals(lostAndFoundRequest.getAnswerThree()) ) {
            //TODO
            //发送新密码给user，并把密码插入到user表中
            String password =  "12345678";

            response.setRetCode(RetCode.SUCCESS.retCode);
            response.setMessage("新密码已发送至您的邮箱，请及时查收并更改密码！");
            return response;
        }

        // 安全问题的答案不正确
        response.setRetCode(RetCode.INPUT_ERROR.retCode);
        response.setMessage("安全问题的答案不正确！");
        return response;
    }

    //TODO
    public FollowPublisherResponse followPublisher(FollowPublisherRequest followPublisherRequest) {
        return null;
    }

    private boolean isNullOrEmpty(String str) {
        return str==null || str.isEmpty();
    }
}
