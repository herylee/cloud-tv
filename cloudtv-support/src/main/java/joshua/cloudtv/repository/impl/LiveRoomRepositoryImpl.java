package joshua.cloudtv.repository.impl;

import joshua.cloudtv.dao.mapper.LiveRoomMapper;
import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.repository.LiveRoomRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by JoshuaShaw on 2016/12/6.
 */
@Repository
public class LiveRoomRepositoryImpl implements LiveRoomRepository {

    @Resource
    private LiveRoomMapper liveRoomMapper;

    public LiveRoom getLiveRoomById(int roomId) {
        return liveRoomMapper.selectByPrimaryKey(roomId);
    }

    public boolean updateLiveRoom(LiveRoom liveRoom) {
        return liveRoomMapper.updateByPrimaryKeySelective(liveRoom) == 1;
    }

    public int addLiveRoomAndGetId(LiveRoom liveRoom) {
        liveRoomMapper.insertSelectiveAndGetId(liveRoom);
        return liveRoom.getId();
    }

    public List<LiveRoom> getLiveRooms(int publishType, int currentPage, int pageRank) {
        int startIndex = currentPage * pageRank;
        int endIndex = startIndex + pageRank;
        // 如果没publishType（即是publishType为0），则返回全部
        return publishType!=0 ? liveRoomMapper.getLiveRoomsSelective(publishType, startIndex, endIndex) :
                liveRoomMapper.getLiveRoomsSelective(null, startIndex, endIndex);
    }

}
