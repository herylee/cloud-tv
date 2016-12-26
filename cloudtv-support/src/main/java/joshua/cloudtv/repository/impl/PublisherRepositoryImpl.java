package joshua.cloudtv.repository.impl;

import joshua.cloudtv.dao.mapper.PublisherMapper;
import joshua.cloudtv.dao.model.Publisher;
import joshua.cloudtv.repository.PublisherRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by JoshuaShaw on 2016/12/5.
 */
@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

    @Resource
    private PublisherMapper publisherMapper;

    public boolean addPublisher(Publisher publisher) {
        return publisherMapper.insertSelective(publisher) == 1;
    }

    public Publisher getPublisherById(int id) {
        return publisherMapper.selectByPrimaryKey(id);
    }

    public boolean updatePublisher(Publisher publisher) {
        return publisherMapper.updateByPrimaryKeySelective(publisher) == 1;
    }

    public boolean modifyPublisherCondition(Publisher publisher) {
        return publisherMapper.updateByPrimaryKeySelective(publisher) == 1;
    }

    public boolean deletePublisherById(int id) {
        return publisherMapper.deleteByPrimaryKey(id) == 1;
    }

}
