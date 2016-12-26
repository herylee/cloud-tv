package joshua.cloudtv.service.impl;


import joshua.cloudtv.constant.PublisherCondition;
import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.constant.UserPermission;
import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.dao.model.Publisher;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.repository.LiveRoomRepository;
import joshua.cloudtv.repository.PublisherRepository;
import joshua.cloudtv.repository.RtmpServerRepository;
import joshua.cloudtv.repository.UserRepository;
import joshua.cloudtv.service.AuthenticationService;
import joshua.cloudtv.utils.DateUtil;
import joshua.cloudtv.utils.Encryptor;
import joshua.cloudtv.utils.IPv4Util;
import joshua.cloudtv.vo.request.*;
import joshua.cloudtv.vo.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @see  AuthenticationService
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LiveRoomRepository liveRoomRepository;

    @Autowired
    private RtmpServerRepository rtmpServerRepository;


    public RegisterResponse register(RegisterRequest registerRequest) {
        RegisterResponse response = new RegisterResponse();

        // 验证邮箱的唯一性
        if( userRepository.existsEmail(registerRequest.getEmail()) ) {
            // 提示该邮箱已经被注册
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("该邮箱已经被注册！");
            // 返回注册信息
            return response;
        }

        // 包括id，邮箱，hash过的口令，用户名，性别。出生年月
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setSalt(new Date().getTime());
        user.setPassword(Encryptor.encrypt(registerRequest.getPassword(), user.getSalt()));
        user.setNickname(registerRequest.getNickname());
        user.setSex((byte)registerRequest.getSex());
        user.setBornTime(DateUtil.stringToDate(registerRequest.getBornTime()));

        // 往user表插入最基本的信息
        userRepository.addUser(user);

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("注册成功！");
        // 返回注册信息
        return response;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        // 通过用户的邮箱和口令的判断
        User user = userRepository.getUserByEmail(loginRequest.getEmail());

        LoginResponse response = new LoginResponse();

        // 该用户不存在 或 密码不正确
        if( user == null || (! Encryptor.encrypt(loginRequest.getPassword(), user.getSalt()).equals(user.getPassword()) ) ) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("用户名或密码错误！");
            return response;
        }

        // 生成一个uuid插入到会话表
        String uuid = UUID.randomUUID().toString();
        userRepository.addSession(uuid, user.getId(), new Date());

        // 将ip插入user表
        user.setLastLoginIp( IPv4Util.ipToLong(loginRequest.getIp()) );
        userRepository.updateUser(user);

        // 返回登录信息
        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("登录成功！");
        response.setUuid(uuid);
        response.setUserId(user.getId());
        return response;
    }

    public ApplyPublisherResponse applyForPublisher(ApplyPublisherRequest applyPublisherRequest) {
        ApplyPublisherResponse response = new ApplyPublisherResponse();

        User user = userRepository.getUserByUUID(applyPublisherRequest.getUuid());
        // 用户还没有登录
        if(user == null) {
            response.setRetCode(RetCode.SERVER_ERROR.retCode);
            response.setMessage("您还没有登录！");
            return response;
        }

        // 用户已经是主播
        if(UserPermission.PUBLISHER.isPublisher(user.getPermission())
                || publisherRepository.getPublisherById(user.getId()) != null ) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("您已经是主播或者已经提交主播申请！");
            return response;
        }

        // 用户已经登录但还不是主播
        Publisher publisher =  new Publisher();
        publisher.setId(user.getId());
        publisher.setRealname(applyPublisherRequest.getRealName());
        publisher.setCertType((byte) applyPublisherRequest.getCertType());
        publisher.setCertId(applyPublisherRequest.getCertId());
        publisher.setProofUrl(applyPublisherRequest.getProofUrl());
        publisher.setPhoneNumber(applyPublisherRequest.getPhoneNumber());

        if( ! publisherRepository.addPublisher(publisher) ) {
            response.setRetCode(RetCode.NOT_FOUND.retCode);
            response.setMessage("对不起，您暂时不能申请成为主播！");
            return response;
        }

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("您的申请已提交，请等待身份核实！");
        return response;
    }

    //TODO 可以修改liveRoom的mapper
    public ValidateLiveStreamResponse validateLiveStream(ValidateLiveStreamRequest validateLiveStreamRequest) {
        ValidateLiveStreamResponse response = new ValidateLiveStreamResponse();

        LiveRoom liveRoom = liveRoomRepository.getLiveRoomById( validateLiveStreamRequest.getName());
        // 不存在该房间
        if(liveRoom == null) {
            response.setRetCode(RetCode.NOT_FOUND.retCode);
            response.setMessage("房间不存在！");
            return response;
        }
        Publisher publisher = publisherRepository.getPublisherById(liveRoom.getPublisherId());
        // 主播状态不正常 或者 令牌不正确
        if( ! liveRoom.getAccessToken().equals(validateLiveStreamRequest.getAccessToken()) ||
               publisher.getPublisherCondition().byteValue() != PublisherCondition.NORMAL.getCondition() ) {
            response.setRetCode(RetCode.INPUT_ERROR.retCode);
            response.setMessage("令牌不正确或主播状态不正常！");
            return response;
        }

        response.setRetCode(RetCode.SUCCESS.retCode);
        response.setMessage("合法的推送！");
        return response;
    }


    public ApplyAccessTokenResponse applyAccessToken(ApplyAccessTokenRequest applyAccessTokenRequest) {
        ApplyAccessTokenResponse response = new ApplyAccessTokenResponse();

        // 获得uuid
        String uuid = applyAccessTokenRequest.getUuid();
        String roomName = applyAccessTokenRequest.getRoomName();
        String posterUrl = applyAccessTokenRequest.getPosterUrl();
        int publishType = applyAccessTokenRequest.getPublishType();

        // 获得User实例
        User user = userRepository.getUserByUUID(uuid);
        // 判断user的权限是不是主播
        if( UserPermission.isPublisher(user.getPermission()) ) {

            // 获得主播实体
            Publisher publisher = publisherRepository.getPublisherById(user.getId());
            // 主播的状态是不是正常
            if(publisher.getPublisherCondition() == PublisherCondition.NORMAL.getCondition()) {
                // 获得房间实体
                LiveRoom liveRoom = liveRoomRepository.getLiveRoomById(publisher.getRoomId());

                String accessToken;
                String publishUrl;

                // 未有accessToken
                if(liveRoom.getAccessToken()==null) {
                    // 生成access_token
                    accessToken = Encryptor.encrypt(UUID.randomUUID().toString(), new Date().getTime());
                    // 判断距离主播最近、负载相对小的的结点，返回推送url
                    //FIXME
                    publishUrl = rtmpServerRepository.getBestRtmpServerInfo(user.getLastLoginIp()).getClusterUrl();

                    // 保存到LiveRoom表中
                    if(roomName!=null) liveRoom.setRoomName(roomName);
                    if(posterUrl!=null) liveRoom.setPosterUrl(posterUrl);
                    if(publishType!=0) liveRoom.setPublishType(publishType);
                    ////重新构造url和token
                    liveRoom.setAccessToken(accessToken);
                    liveRoom.setPublishUrl(publishUrl);
                    liveRoomRepository.updateLiveRoom(liveRoom);
                } else { // 未有accessToken直接返回
                    accessToken = liveRoom.getAccessToken();
                    publishUrl = liveRoom.getPublishUrl();
                }


                //response
                response.setRetCode(RetCode.SUCCESS.retCode);
                response.setMessage("返回成功！");
                response.setAccessToken(liveRoom.getId().toString()+"?accessToken="+accessToken);
                response.setPublishUrl(publishUrl);
                return response;
            }
        }
        response.setRetCode(RetCode.INPUT_ERROR.retCode);
        response.setMessage("您还不是主播，该功能不可用！");
        return response;
    }

}
