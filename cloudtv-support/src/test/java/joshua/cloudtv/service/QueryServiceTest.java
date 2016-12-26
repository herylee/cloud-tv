package joshua.cloudtv.service;

import joshua.cloudtv.utils.JsonKit;
import joshua.cloudtv.vo.request.GetLiveRoomsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JoshuaShaw on 2016/12/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring-mybaits.xml"})
public class QueryServiceTest {

    @Autowired
    private QueryService queryService;

    @Test
    public void getLiveRooms() {
        GetLiveRoomsRequest request = new GetLiveRoomsRequest();
        request.setCurrentPage(0);
        request.setPageRank(6);
        request.setPublishTypeId(0);// 不分直播类型
        JsonKit.printJson( queryService.getLiveRooms(request) );
    }
}
