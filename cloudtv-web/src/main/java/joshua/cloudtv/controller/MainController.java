package joshua.cloudtv.controller;

import joshua.cloudtv.service.QueryService;
import joshua.cloudtv.service.UserService;
import joshua.cloudtv.vo.request.GetLiveRoomsRequest;
import joshua.cloudtv.vo.response.GetLiveRoomsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by JoshuaShaw on 2016/12/12.
 */
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private QueryService queryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@CookieValue(value="uuid",required=false) String uuid, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Model model) {

        GetLiveRoomsRequest getLiveRoomsRequest = new GetLiveRoomsRequest();
        getLiveRoomsRequest.setPublishTypeId(0);
        getLiveRoomsRequest.setCurrentPage(0);
        getLiveRoomsRequest.setPageRank(6);
        GetLiveRoomsResponse getLiveRoomsResponse = queryService.getLiveRooms(getLiveRoomsRequest);

        model.addAttribute("liveRoomslist", getLiveRoomsResponse.getLiveRoomsList());
        return "index";
    }

}
