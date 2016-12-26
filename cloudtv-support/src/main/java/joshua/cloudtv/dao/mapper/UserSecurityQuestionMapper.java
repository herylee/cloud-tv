package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.UserSecurityQuestion;

public interface UserSecurityQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSecurityQuestion record);

    int insertSelective(UserSecurityQuestion record);

    UserSecurityQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSecurityQuestion record);

    int updateByPrimaryKey(UserSecurityQuestion record);
}