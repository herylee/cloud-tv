package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.SystemParameter;

public interface SystemParameterMapper {
    int deleteByPrimaryKey(String key);

    int insert(SystemParameter record);

    int insertSelective(SystemParameter record);

    SystemParameter selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(SystemParameter record);

    int updateByPrimaryKey(SystemParameter record);
}