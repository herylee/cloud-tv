package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.UserSecurity;

public interface UserSecurityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSecurity record);

    int insertSelective(UserSecurity record);

    UserSecurity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSecurity record);

    int updateByPrimaryKey(UserSecurity record);
}