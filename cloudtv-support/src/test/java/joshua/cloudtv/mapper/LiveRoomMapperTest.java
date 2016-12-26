package joshua.cloudtv.mapper;

import joshua.cloudtv.dao.mapper.LiveRoomMapper;
import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.utils.JsonKit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring-mybaits.xml"})
public class LiveRoomMapperTest {

    @Autowired
    private LiveRoomMapper liveRoomMapper;

    @Test
    public void addLiveRoom() {
        LiveRoom liveRoom = new LiveRoom();
        liveRoom.setPublisherId(1100014);
        liveRoom.setRoomName("joshuashaw的房间"); // 创建初始化的房间名

        int roomId = liveRoomMapper.insertSelectiveAndGetId(liveRoom);

        Assert.assertEquals((int) liveRoom.getId(), 100);
    }

    @Test
    public void getLiveRoomById() {
        JsonKit.printJson(liveRoomMapper.getLiveRoomsSelective(null, 0, 10));
    }

    @Test
    public void getLiveRooms() {
        JsonKit.printJson(liveRoomMapper.getLiveRoomsSelective(null, 0, 6));
    }
}