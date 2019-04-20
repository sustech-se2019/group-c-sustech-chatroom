package sim.DaoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sim.mapper.UsersMapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import sim.Dao.UserDao;
import sim.pojo.Users;

/**
 * This class is to implement UserDao interface.
 */
@Service
public class UserDaoImpl implements UserDao {

    @Autowired(required = false)
    private UsersMapper userMapper;
    /**
     * implement queryUsernameIsExist().
     *
     * @param username the username
     * @return the boolean
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users user = new Users();
        user.setUsername(username);

        Users result = userMapper.selectOne(user);

        return result != null;
    }
    /**
     * implement queryUserForLogin()
     *
     * @param username the username
     * @param pwd      the pwd
     * @return the users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String pwd) {
        Example userExample = new Example(Users.class);
        Criteria criteria = userExample.createCriteria();

        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", pwd);

        return userMapper.selectOneByExample(userExample);
    }
    /**
     * implement createUser().
     *
     * @param user the user
     * @return the users
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users createUser(Users user) {
        userMapper.insert(user);

        return user;
    }
    /**
     * implement updateUserInfo();
     *
     * @param user the user
     * @return the users
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(Users user) {
        userMapper.updateByPrimaryKeySelective(user);
        return queryUserById(user.getId());
    }
    /**
     * Query user by id, if exist return that user.
     *
     * @param userId the user id
     * @return the users
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
