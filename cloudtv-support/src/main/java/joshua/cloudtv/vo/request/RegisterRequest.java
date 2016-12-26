package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/11/29.
 *
 * 注册值对象
 */
public class RegisterRequest {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 口令
     */
    private String password;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 性别（0女1男）
     */
    private int sex;

    /**
     * 出生年月
     */
    private String bornTime;

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
