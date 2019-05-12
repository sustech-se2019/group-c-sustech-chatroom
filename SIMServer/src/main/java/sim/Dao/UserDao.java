package sim.Dao;

import sim.netty.ChatMsg;
import sim.pojo.ChatHistory;
import sim.pojo.Users;

import java.util.List;

/**
 * The interface User dao, to provide the interface of database access.
 */
public interface UserDao {

    /**
     * if Query username is exist return false, or true.
     *
     * @param username the username
     * @return the boolean
     * @Description: judge if the user name is already existed
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * Query user name in database..
     *
     * @param username the username
     * @param pwd      the pwd
     * @return {@link Users}
     * @Description: search for the user name and return the user if exist.
     */
    public Users queryUserForLogin(String username, String pwd);

    /**
     * Create user users.
     *
     * @param user the user
     * @return the users
     * @Description: register user
     */
    public Users createUser(Users user);

    /**
     * Update user info users.
     *
     * @param user the user
     * @return the users
     * @Description: change user information
     */
    public Users updateUserInfo(Users user);
    /**
     * query user by id.
     *
     * @param userId the user ID
     * @return the users
     * @Description: query user by id and return users
     */
    public Users queryUserById(String userId);

    /**
     * @Description: 保存聊天消息到数据库
     */
    public String saveMsg(ChatMsg chatMsg);

    /**
     * @Description: 批量签收消息
     */
    public void updateMsgSigned(List<String> msgIdList);

    /**
     * @Description: 获取未签收消息列表
     */
    public List<ChatHistory> getUnReadMsgList(String acceptUserId);
}
