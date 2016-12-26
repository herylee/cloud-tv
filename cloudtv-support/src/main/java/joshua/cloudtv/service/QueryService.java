package joshua.cloudtv.service;

import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.vo.request.GetLiveRoomsRequest;
import joshua.cloudtv.vo.response.GetLiveRoomsResponse;

import java.util.List;

/**
 * 查询服务接口
 */
public interface QueryService {

    /**
     * 获得直播列表
     *
     * @param getLiveRoomsRequest
     * @return
     */
    GetLiveRoomsResponse getLiveRooms(GetLiveRoomsRequest getLiveRoomsRequest);

    LiveRoom getLiveRoomById(int roomId);

}
