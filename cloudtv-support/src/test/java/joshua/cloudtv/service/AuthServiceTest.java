package joshua.cloudtv.service;

import joshua.cloudtv.service.AuthenticationService;
import joshua.cloudtv.utils.JsonKit;
import joshua.cloudtv.vo.request.*;
import joshua.cloudtv.vo.response.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JoshuaShaw on 2016/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring-mybaits.xml"})
public class AuthServiceTest {

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void registerTest() {
        RegisterRequest request = new RegisterRequest();

        request.setEmail("455532734@qq.com");
        request.setPassword("12345678");
        request.setNickname("小虫虫");
        request.setSex(1);
        request.setBornTime("1992-01-02");

        RegisterResponse response = authenticationService.register(request);
        JsonKit.printJson(response);
    }

    @Test
    public void LoginTest() {
        LoginRequest request = new LoginRequest();

        request.setEmail("455532734@qq.com");
        request.setPassword("12345678");
        request.setIp("172.18.219.85");

        LoginResponse response = authenticationService.login(request);
        JsonKit.printJson(response);
        Assert.assertEquals(response.getRetCode(), 200);
    }

    @Test
    public void applyForPublisherTest() {
        ApplyPublisherRequest request = new ApplyPublisherRequest();

        request.setUuid("e733e3b0-45ba-4636-97be-b8fa65a99959");
        request.setRealName("肖长洲");
        request.setCertType(0);
        request.setCertId("440552111174441414");
        request.setProofUrl("http://test.com/imgs/test.jpg");
        request.setPhoneNumber("13374125896");

        ApplyPublisherResponse response = authenticationService.applyForPublisher(request);
        JsonKit.printJson(response);

    }

    @Test
    public void applyAccessToken() {
        ApplyAccessTokenRequest request = new ApplyAccessTokenRequest();
        request.setUuid("e733e3b0-45ba-4636-97be-b8fa65a99959");

        ApplyAccessTokenResponse response = authenticationService.applyAccessToken(request);

        JsonKit.printJson(response);
    }

    @Test
    public void validateLiveStreamTest() {
        ValidateLiveStreamRequest request = new ValidateLiveStreamRequest();
        request.setAddr("172.18.219.10");
        request.setAccessToken("2da6d1e942657fe9af1414a4987a61d493dbbcd2");
        request.setName(100005);

        ValidateLiveStreamResponse response = authenticationService.validateLiveStream(request);

        JsonKit.printJson(response);
    }

}
