package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */
public class ModifyConditionRequest {

    /**
     * 主播id
     */
    private int publisherId;

    /**
     * 主播状态
     */
    private int publisherCondition;

    /**
     * 房间id
     */
    private int roomId;

    /**
     * 房间id
     */
    private int roomCondition;

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getPublisherCondition() {
        return publisherCondition;
    }

    public void setPublisherCondition(int publisherCondition) {
        this.publisherCondition = publisherCondition;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomCondition() {
        return roomCondition;
    }

    public void setRoomCondition(int roomCondition) {
        this.roomCondition = roomCondition;
    }
}
