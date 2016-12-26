package joshua.cloudtv.service;


import joshua.cloudtv.vo.request.*;
import joshua.cloudtv.vo.response.*;

/**
 * 认证服务接口
 */
public interface AuthenticationService {

    /**
     * 注册
     *
     * @param registerRequest
     * @return
     */
    RegisterResponse register(RegisterRequest registerRequest);


    /**
     * 登录
     *
     * @param loginRequest
     * @return
     */
    LoginResponse login(LoginRequest loginRequest);


    /**
     * 申请成为主播
     *
     * @param applyPublisherRequest
     * @return
     */
    ApplyPublisherResponse applyForPublisher(ApplyPublisherRequest applyPublisherRequest);


    /**
     * 验证直播流合法性
     *
     * @param validateLiveStreamRequest
     * @return
     */
    ValidateLiveStreamResponse validateLiveStream(ValidateLiveStreamRequest validateLiveStreamRequest);

    /**
     * 主播申请推送令牌
     *
     * @param applyAccessTokenRequest
     * @return
     */
    ApplyAccessTokenResponse applyAccessToken(ApplyAccessTokenRequest applyAccessTokenRequest);

}
