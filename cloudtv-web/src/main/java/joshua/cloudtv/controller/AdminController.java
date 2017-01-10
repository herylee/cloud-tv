package joshua.cloudtv.controller;

import joshua.cloudtv.service.AdminService;
import joshua.cloudtv.service.QueryService;
import joshua.cloudtv.vo.request.GetLiveRoomsRequest;
import joshua.cloudtv.vo.response.GetCadidatePublisherResponse;
import joshua.cloudtv.vo.response.GetLiveRoomsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by JoshuaShaw on 2016/12/29.
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String index(HttpServletResponse httpServletResponse, Model model) {

        //GetCadidatePublisherResponse getCadidatePublisherResponse = queryService.getCadidatePublishers(null);
        GetLiveRoomsRequest getLiveRoomsRequest = new GetLiveRoomsRequest();
        getLiveRoomsRequest.setPublishTypeId(0);
        getLiveRoomsRequest.setCurrentPage(0);
        getLiveRoomsRequest.setPageRank(1000);
        GetLiveRoomsResponse getLiveRoomsResponse = queryService.getLiveRooms(getLiveRoomsRequest);

        //model.addAttribute("cadidatePublishers", getCadidatePublisherResponse.getPublisherList());
        model.addAttribute("liveRoomslist", getLiveRoomsResponse.getLiveRoomsList());
        return "admin";
    }
}
