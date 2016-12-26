package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/11/30.
 */
public class LoginRequest {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 口令
     */
    private String password;

    /**
     * 用户登录的ip
     */
    private String ip;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
