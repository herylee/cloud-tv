package joshua.cloudtv.vo.response;

import joshua.cloudtv.dao.model.Publisher;

import java.util.List;

/**
 * Created by JoshuaShaw on 2016/12/29.
 */
public class GetCadidatePublisherResponse extends BaseResponse {

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    public void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }

    private List<Publisher> publisherList;
}
