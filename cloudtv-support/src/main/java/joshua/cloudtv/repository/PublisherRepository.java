package joshua.cloudtv.repository;


import joshua.cloudtv.dao.model.Publisher;

/**
 * 主播仓储层
 */
public interface PublisherRepository {

    boolean addPublisher(Publisher publisher);

    Publisher getPublisherById(int id);

    boolean updatePublisher(Publisher publisher);// 主播使用

    boolean modifyPublisherCondition(Publisher publisher);// 只限管理员使用

    boolean deletePublisherById(int id);// 不使用


}
