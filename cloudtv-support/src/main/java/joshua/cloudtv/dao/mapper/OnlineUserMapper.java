package joshua.cloudtv.dao.mapper;

import joshua.cloudtv.dao.model.OnlineUser;
import joshua.cloudtv.dao.model.OnlineUserKey;

public interface OnlineUserMapper {
    int deleteByPrimaryKey(OnlineUserKey key);

    int insert(OnlineUser record);

    int insertSelective(OnlineUser record);

    OnlineUser selectByPrimaryKey(OnlineUserKey key);

    OnlineUser selectByUUID(String uuid);

    int updateByPrimaryKeySelective(OnlineUser record);

    int updateByPrimaryKey(OnlineUser record);
}