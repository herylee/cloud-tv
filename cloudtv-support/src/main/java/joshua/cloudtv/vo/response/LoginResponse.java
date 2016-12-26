package joshua.cloudtv.vo.response;

/**
 * Created by JoshuaShaw on 2016/11/30.
 */
public class LoginResponse extends BaseResponse {

    /**
     * 会话对应的一个凭证
     */
    private String uuid;

    /**
     * 用户的唯一id
     */
    private int userId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
