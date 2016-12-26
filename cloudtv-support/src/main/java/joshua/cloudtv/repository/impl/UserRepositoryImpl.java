package joshua.cloudtv.repository.impl;

import joshua.cloudtv.dao.mapper.OnlineUserMapper;
import joshua.cloudtv.dao.mapper.UserMapper;
import joshua.cloudtv.dao.mapper.UserSecurityMapper;
import joshua.cloudtv.dao.model.OnlineUser;
import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.dao.model.UserSecurity;
import joshua.cloudtv.repository.UserRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by JoshuaShaw on 2016/12/5.
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Resource
    private UserMapper userMapper;

    @Resource
    private OnlineUserMapper onlineUserMapper;

    @Resource
    private UserSecurityMapper userSecurityMapper;

    public boolean existsEmail(String email) {
        return userMapper.selectByEmail(email) != null;
    }

    public boolean addUser(User user) {
        return userMapper.insertSelective(user) == 1;
    }

    public boolean deleteUserById(int id) {
        return userMapper.deleteByPrimaryKey(id) == 1;
    }

    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public User getUserByUUID(String uuid) {
        OnlineUser onlineUser = onlineUserMapper.selectByUUID(uuid);
        if(onlineUser != null) {
            return userMapper.selectByPrimaryKey( onlineUser.getUserid() );
        }
        return null;
    }

    public boolean updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    public boolean updateUserPermission(User user) {
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    public boolean addSession(String uuid, int userId, Date date) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setCookieUuid(uuid);
        onlineUser.setUserid(userId);
        onlineUser.setLoginDatetime(date);

        return onlineUserMapper.insert(onlineUser) == 1;
    }

    public boolean updateSession(String uuid, int userId, Date date) {
        OnlineUser onlineUser = new OnlineUser();
        onlineUser.setCookieUuid(uuid);
        onlineUser.setUserid(userId);
        onlineUser.setLoginDatetime(date);

        return onlineUserMapper.updateByPrimaryKey(onlineUser) == 1;
    }

    public UserSecurity getUserSecurityById(int id) {
        return userSecurityMapper.selectByPrimaryKey(id);
    }

    public boolean addUserSecurity(UserSecurity userSecurity) {
        return userSecurityMapper.insert(userSecurity) == 1;
    }

}
