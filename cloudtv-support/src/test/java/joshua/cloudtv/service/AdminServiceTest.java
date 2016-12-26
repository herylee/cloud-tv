package joshua.cloudtv.service;

import joshua.cloudtv.service.AdminService;
import joshua.cloudtv.utils.JsonKit;
import joshua.cloudtv.vo.request.ValidatePublisherRequest;
import joshua.cloudtv.vo.response.ValidatePublisherResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JoshuaShaw on 2016/12/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring-mybaits.xml"})
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Test
    public void validatePublisherTest() {
        ValidatePublisherRequest request = new ValidatePublisherRequest();
        request.setUserId(1100034);

        ValidatePublisherResponse response = adminService.validatePublisher(request);
        JsonKit.printJson(response);

    }
}
