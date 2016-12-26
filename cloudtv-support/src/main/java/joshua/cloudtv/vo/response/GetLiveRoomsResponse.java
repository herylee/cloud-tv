package joshua.cloudtv.vo.response;

import joshua.cloudtv.vo.view.LiveRoomView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoshuaShaw on 2016/12/11.
 */
public class GetLiveRoomsResponse extends BaseResponse {
    private List<LiveRoomView> liveRoomsList;

    public List<LiveRoomView> getLiveRoomsList() {
        return liveRoomsList;
    }

    public void setLiveRoomsList(List<LiveRoomView> liveRoomsList) {
        this.liveRoomsList = liveRoomsList;
    }
}
