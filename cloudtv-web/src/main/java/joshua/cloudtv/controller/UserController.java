package joshua.cloudtv.controller;

import joshua.cloudtv.dao.model.LiveRoom;
import joshua.cloudtv.service.PublisherService;
import joshua.cloudtv.service.UserService;
import joshua.cloudtv.utils.JsonKit;
import joshua.cloudtv.vo.request.UpdateLiveRoomInfosRequest;
import joshua.cloudtv.vo.request.UpdateUserInfosRequest;
import joshua.cloudtv.vo.response.UpdateLiveRoomInfosResponse;
import joshua.cloudtv.vo.response.UpdateUserInfosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by JoshuaShaw on 2016/12/16.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PublisherService publisherService;

    // 用户个人主页
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String userMainPage(@PathVariable("id") int id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "user";
    }

    // 修改用户基本信息
    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String modifyUser(@PathVariable("id") int id, UpdateUserInfosRequest updateUserInfosRequest, HttpSession httpSession, Model model) {
        String uuid = (String) httpSession.getAttribute("uuid");
        updateUserInfosRequest.setUuid(uuid);
        UpdateUserInfosResponse response = userService.updateUserInfos(updateUserInfosRequest);
        return response.toJson();
    }

    // 用户申请为主播
    @RequestMapping(value = "/{id}/verify", method = RequestMethod.GET)
    public String apply(@PathVariable("id") int id, Model model) {
        return "verify";
    }

    // 直播房间信息界面
    @RequestMapping(value = "/{id}/liveRoom", method = RequestMethod.GET)
    public String getLiveRoomInfos(@PathVariable("id") int id, HttpServletRequest httpServletRequest, Model model) {
        LiveRoom liveRoom = publisherService.getLiveRoomByPublisherId(id);
        model.addAttribute(liveRoom);
        return "liveRoomEdit";
    }

    // 修改直播房间信息
    @RequestMapping(value = "/{id}/liveRoom", method = RequestMethod.POST)
    @ResponseBody
    public String updateLiveRoomInfos(@PathVariable("id") int id, UpdateLiveRoomInfosRequest updateLiveRoomInfosRequest, HttpSession httpSession) {
        String uuid = (String) httpSession.getAttribute("uuid");
        updateLiveRoomInfosRequest.setUuid(uuid);
        UpdateLiveRoomInfosResponse updateLiveRoomInfosResponse = publisherService.updateLiveRoomInfos(updateLiveRoomInfosRequest);
        return updateLiveRoomInfosResponse.toJson();
    }
}
