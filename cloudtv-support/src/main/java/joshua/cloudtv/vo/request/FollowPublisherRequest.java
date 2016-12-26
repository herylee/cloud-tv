package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/2.
 */
public class FollowPublisherRequest {

    /**
     * 用户会话凭证
     */
    private String uuid;

    /**
     * 主播id
     */
    private int publisherId;

    /**
     * 关注类型
     */
    private int followType;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getFollowType() {
        return followType;
    }

    public void setFollowType(int followType) {
        this.followType = followType;
    }
}
