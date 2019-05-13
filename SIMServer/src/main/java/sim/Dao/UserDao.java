package sim.Dao;

import sim.pojo.Users;
import sim.pojo.vo.FriendRequestVO;
import sim.pojo.vo.MyFriendsVO;

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



    public Users queryUserById(String userId);

    public Integer preconditionSearchFriends(String myUserId, String friendUsername);

    public Users queryUserInfoByUsername(String username);

    public void sendFriendRequest(String myUserId, String friendUsername);

    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

    public void deleteFriendRequest(String sendUserId, String acceptUserId);

    public void passFriendRequest(String sendUserId, String acceptUserId);

    public List<MyFriendsVO> queryMyFriends(String userId);
}
