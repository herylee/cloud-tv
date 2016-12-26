package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */
public class ValidateLiveStreamRequest {

    private int name;

    private String accessToken;

    private String addr;

    private String app;



    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
