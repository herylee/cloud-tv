package joshua.cloudtv.service;


import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.vo.request.FollowPublisherRequest;
import joshua.cloudtv.vo.request.LostAndFoundRequest;
import joshua.cloudtv.vo.request.SetSecurityQuestionRequest;
import joshua.cloudtv.vo.request.UpdateUserInfosRequest;
import joshua.cloudtv.vo.response.FollowPublisherResponse;
import joshua.cloudtv.vo.response.LostAndFoundResponse;
import joshua.cloudtv.vo.response.SetSecurityQuestionResponse;
import joshua.cloudtv.vo.response.UpdateUserInfosResponse;

/**
 * 用户服务接口
 */
public interface UserService {


    /**
     * 通过凭证获得的用户实体
     *
     * @param uuid
     * @return
     */
    User getSessionUser(String uuid);

    /**
     * 通过userId获得User
     *
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * 更新用户信息
     *
     * @param updateUserInfosRequest
     * @return
     */
    UpdateUserInfosResponse updateUserInfos(UpdateUserInfosRequest updateUserInfosRequest);

    /**
     * 第一次设置安全问题
     *
     * @param request
     * @return
     */
    SetSecurityQuestionResponse setSecurityQuestion(SetSecurityQuestionRequest request);

    /**
     * 找回密码
     *
     * @param lostAndFoundRequest
     * @return
     */
    LostAndFoundResponse findPassword(LostAndFoundRequest lostAndFoundRequest);

    /**
     * 关注主播
     *
     * @param followPublisherRequest
     * @return
     */
    FollowPublisherResponse followPublisher(FollowPublisherRequest followPublisherRequest);
}
