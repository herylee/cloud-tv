package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.LiveRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiveRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelectiveAndGetId(LiveRoom record);

    LiveRoom selectByPrimaryKey(Integer id);

    List<LiveRoom> getLiveRoomsSelective(@Param("publisherType") Integer publishType, @Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);

    int updateByPrimaryKeySelective(LiveRoom record);

    int updateByPrimaryKey(LiveRoom record);
}