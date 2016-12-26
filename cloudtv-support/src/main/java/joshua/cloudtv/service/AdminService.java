package joshua.cloudtv.service;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */

import joshua.cloudtv.vo.request.ModifyConditionRequest;
import joshua.cloudtv.vo.request.ValidatePublisherRequest;
import joshua.cloudtv.vo.response.ModifyConditionResponse;
import joshua.cloudtv.vo.response.ValidatePublisherResponse;

/**
 * 管理员服务接口
 */
public interface AdminService {

    /**
     * 验证主播的合法性
     *
     * @param validatePublisherRequest
     * @return
     */
    ValidatePublisherResponse validatePublisher(ValidatePublisherRequest validatePublisherRequest);

    /**
     * 修改主播、房间状态
     *
     * @param modifyConditionRequest
     * @return
     */
    ModifyConditionResponse modifyCondition(ModifyConditionRequest modifyConditionRequest);

}
