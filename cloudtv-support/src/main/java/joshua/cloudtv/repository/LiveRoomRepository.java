package joshua.cloudtv.repository;

import joshua.cloudtv.dao.model.LiveRoom;

import java.util.List;

/**
 * 直播房间仓储层
 */
public interface LiveRoomRepository {

    LiveRoom getLiveRoomById(int roomId);

    boolean updateLiveRoom(LiveRoom liveRoom);

    int addLiveRoomAndGetId(LiveRoom liveRoom);

    List<LiveRoom> getLiveRooms(int publishType, int currentPage, int pageRank);
}
