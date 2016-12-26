package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.PublisherCondition;

public interface PublisherConditionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublisherCondition record);

    int insertSelective(PublisherCondition record);

    PublisherCondition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PublisherCondition record);

    int updateByPrimaryKey(PublisherCondition record);
}