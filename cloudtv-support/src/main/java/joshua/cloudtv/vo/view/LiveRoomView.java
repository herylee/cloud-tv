package joshua.cloudtv.vo.view;

/**
 * Created by JoshuaShaw on 2016/12/11.
 */
public class LiveRoomView {
    private String posterUrl;
    private int publisherId;
    private String publisherNickName;
    private int publishTypeId;
    private String publishTypeDescription;
    private int liveWatchCount;
    private int roomId;
    private String roomName;
    private String roomDescription;

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getPublisherNickName() {
        return publisherNickName;
    }

    public void setPublisherNickName(String publisherNickName) {
        this.publisherNickName = publisherNickName;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getPublishTypeId() {
        return publishTypeId;
    }

    public void setPublishTypeId(int publishTypeId) {
        this.publishTypeId = publishTypeId;
    }

    public String getPublishTypeDescription() {
        return publishTypeDescription;
    }

    public void setPublishTypeDescription(String publishTypeDescription) {
        this.publishTypeDescription = publishTypeDescription;
    }

    public int getLiveWatchCount() {
        return liveWatchCount;
    }

    public void setLiveWatchCount(int liveWatchCount) {
        this.liveWatchCount = liveWatchCount;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}
