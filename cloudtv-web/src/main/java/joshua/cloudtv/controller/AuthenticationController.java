package joshua.cloudtv.controller;


import joshua.cloudtv.constant.RetCode;
import joshua.cloudtv.service.AuthenticationService;
import joshua.cloudtv.utils.JsonKit;
import joshua.cloudtv.vo.request.*;
import joshua.cloudtv.vo.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    // 用户注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(RegisterRequest registerRequest) {
        RegisterResponse registerResponse = authenticationService.register(registerRequest);
        return registerResponse.toJson();
    }

    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(LoginRequest loginRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LoginResponse loginResponse = authenticationService.login(loginRequest);
        JsonKit.printJson(loginRequest);
        // 登陆成功
        if (loginResponse.getRetCode() == RetCode.SUCCESS.retCode) {
            httpServletRequest.getSession().setAttribute("uuid", loginResponse.getUuid());
        }
        return loginResponse.toJson();
    }

    // 用户登出
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletRequest.getSession().removeAttribute("uuid");
        return "redirect:/";
    }


    // 申请成为主播
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ResponseBody
    public String apply(ApplyPublisherRequest applyPublisherRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uuid = (String) httpServletRequest.getSession().getAttribute("uuid");
        applyPublisherRequest.setUuid(uuid);
        ApplyPublisherResponse applyPublisherResponse = authenticationService.applyForPublisher(applyPublisherRequest);
        applyPublisherResponse.printJson();
        return applyPublisherResponse.toJson();
    }

    // 申请直播连接
    @RequestMapping(value = "/accessToken", method = RequestMethod.POST)
    @ResponseBody
    public String applyAccessToken(ApplyAccessTokenRequest applyAccessTokenRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String uuid = (String) httpServletRequest.getSession().getAttribute("uuid");
        applyAccessTokenRequest.setUuid(uuid);
        ApplyAccessTokenResponse applyAccessTokenResponse = authenticationService.applyAccessToken(applyAccessTokenRequest);
        applyAccessTokenResponse.printJson();
        return applyAccessTokenResponse.toJson();
    }

    // 验证直播流
    @RequestMapping(value = "/stream", method = RequestMethod.POST)
    @ResponseBody
    public String stream(ValidateLiveStreamRequest validateLiveStreamRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ValidateLiveStreamResponse validateLiveStreamResponse = authenticationService.validateLiveStream(validateLiveStreamRequest);
        httpServletResponse.setStatus(validateLiveStreamResponse.getRetCode());
        JsonKit.printJson( validateLiveStreamRequest );
        validateLiveStreamResponse.printJson();
        return validateLiveStreamResponse.toJson();
    }

    //TODO
    // 结束直播连接
    @RequestMapping(value = "/kkk", method = RequestMethod.POST)
    public String kkk(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }

}
