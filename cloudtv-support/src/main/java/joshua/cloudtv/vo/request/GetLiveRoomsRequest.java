package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/1.
 */
public class GetLiveRoomsRequest {

    /**
     * 直播类型id
     */
    private int publishTypeId;

    /**
     * 当前页数
     */
    private int currentPage;

    /**
     * 页数范围
     */
    private int pageRank;

    public int getPublishTypeId() {
        return publishTypeId;
    }

    public void setPublishTypeId(int publishTypeId) {
        this.publishTypeId = publishTypeId;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageRank() {
        return pageRank;
    }

    public void setPageRank(int pageRank) {
        this.pageRank = pageRank;
    }
}
