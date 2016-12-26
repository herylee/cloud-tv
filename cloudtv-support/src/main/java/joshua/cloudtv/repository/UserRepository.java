package joshua.cloudtv.repository;

import joshua.cloudtv.dao.model.User;
import joshua.cloudtv.dao.model.UserSecurity;

import java.util.Date;

/**
 * 用户仓储层
 */
public interface UserRepository {

    boolean existsEmail(String email);

    boolean addUser(User user);

    boolean deleteUserById(int id);// 不使用

    User getUserById(int id);

    User getUserByEmail(String email);

    User getUserByUUID(String uuid);

    boolean updateUser(User user);

    boolean updateUserPermission(User user);

    boolean addSession(String uuid, int userId, Date date);

    boolean updateSession(String uuid, int userId, Date date);

    UserSecurity getUserSecurityById(int id);

    boolean addUserSecurity(UserSecurity userSecurity);
}
