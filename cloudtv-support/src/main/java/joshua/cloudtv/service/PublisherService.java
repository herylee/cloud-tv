package joshua.cloudtv.service;

import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.vo.request.UpdateLiveRoomInfosRequest;
import joshua.cloudtv.vo.response.UpdateLiveRoomInfosResponse;
import joshua.cloudtv.vo.view.LiveRoomView;

/**
 * 主播服务接口
 */
public interface PublisherService {

    /**
     * 更新直播房间信息
     *
     * @param updateLiveRoomInfosRequest
     * @return
     */
    UpdateLiveRoomInfosResponse updateLiveRoomInfos(UpdateLiveRoomInfosRequest updateLiveRoomInfosRequest);

    LiveRoom getLiveRoomByPublisherId(int publisherId);
}
