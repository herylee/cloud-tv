package joshua.cloudtv.vo.request;

/**
 * Created by JoshuaShaw on 2016/12/29.
 */
public class GetCadidatePublisherRequest {
    private int currentPage;
    private int pageRank;

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
