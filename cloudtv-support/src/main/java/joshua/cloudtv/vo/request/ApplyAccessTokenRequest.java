package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */
public class ApplyAccessTokenRequest {

    /**
     * 用户会话凭证
     */
    private String uuid;

    /**
     * 房间名
     */
    private String roomName;

    /**
     * 直播封面
     */
    private String posterUrl;

    /**
     * 直播类型
     */
    private int publishType;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getPublishType() {
        return publishType;
    }

    public void setPublishType(int publishType) {
        this.publishType = publishType;
    }
}
