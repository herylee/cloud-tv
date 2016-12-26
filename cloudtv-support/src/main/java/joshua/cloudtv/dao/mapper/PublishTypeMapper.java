package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.PublishType;

public interface PublishTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PublishType record);

    int insertSelective(PublishType record);

    PublishType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PublishType record);

    int updateByPrimaryKey(PublishType record);
}