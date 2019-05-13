package sim.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sim.Dao.UserDao;
import sim.enums.OperatorFriendRequestTypeEnum;
import sim.pojo.ChatHistory;
import sim.pojo.Users;
import sim.pojo.vo.MyFriendsVO;
import sim.pojo.vo.UsersVO;
import sim.utils.JSONResult;
import org.n3r.idworker.Sid;
import sim.enums.SearchFriendsStatusEnum;

import java.util.List;

/**
 * The Login and regist controller,witch is used to reply the request and update server.
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;
    /**
     * Login json result.
     *
     * @param user the user
     * @return the json result
     * @throws Exception the exception
     */
    @PostMapping("/login")
    public JSONResult login(@RequestBody Users user) throws Exception{

        // 0. 判断用户名和密码不能为空
        if (user.getUsername() == null
                || user.getPassword() == null) {
            return JSONResult.errorMsg("用户名或密码不能为空...");
        }

        Users userResult = userDao.queryUserForLogin(user.getUsername(),
                user.getPassword());
        if (userResult == null) {
            return JSONResult.errorMsg("用户名或密码不正确...");
        }else{
            UsersVO userVO = new UsersVO();
            BeanUtils.copyProperties(userResult, userVO);

            return JSONResult.ok(userVO);
        }

    }

    /**
     * Regist json result. Reply to request.
     *
     * @param user the user information
     * @return {@link JSONResult} message.
     * @throws Exception the exception
     */
    @PostMapping("/regist")
    public JSONResult regist(@RequestBody Users user) throws Exception{

        // 0. 判断用户名和密码不能为空
        if (user.getUsername() == null
                || user.getPassword() == null) {
            return JSONResult.errorMsg("用户名或密码不能为空...");
        }

        Boolean userExit = userDao.queryUsernameIsExist(user.getUsername());
        if (userExit) {
            return JSONResult.errorMsg("用户已存在...");
        }else{
            user.setId(Sid.nextShort());
            user.setNickname(user.getUsername());
            Users userResult = userDao.createUser(user);
            if (userResult == null){
                return JSONResult.errorMsg("创建用户失败...");
            }else{
                UsersVO userVO = new UsersVO();
                BeanUtils.copyProperties(userResult, userVO);
                return JSONResult.ok(userVO);
            }
        }

    }

    /**
     * Search user with self id and friend user name.
     *
     * @param myUserId id of current user
     * @param friendUsername the friend username
     * @return {@link JSONResult} message.
     * @throws Exception the exception
     */
    @PostMapping("/search")
    public JSONResult searchUser(String myUserId, String friendUsername)
            throws Exception {

        // 0. 判断 myUserId friendUsername 不能为空
        if (myUserId == null || friendUsername == null) {
            return JSONResult.errorMsg("");
        }

        Integer status = userDao.preconditionSearchFriends(myUserId, friendUsername);
        if (status.equals(SearchFriendsStatusEnum.SUCCESS.status)) {
            Users user = userDao.queryUserInfoByUsername(friendUsername);
            UsersVO userVO = new UsersVO();
            BeanUtils.copyProperties(user, userVO);
            return JSONResult.ok(userVO);
        } else {
            String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
            return JSONResult.errorMsg(errorMsg);
        }
    }




    /**
     * Send friend request from from my user id to friend user name.
     *
     * @param myUserId id of current user
     * @param friendUsername the friend username
     * @return {@link JSONResult} message.
     * @throws Exception the exception
     */
    @PostMapping("/addFriendRequest")
    public JSONResult addFriendRequest(String myUserId, String friendUsername)
            throws Exception {

        // 0. 判断 myUserId friendUsername 不能为空
        if (myUserId == null
                || friendUsername == null) {
            return JSONResult.errorMsg("");
        }

        // 前置条件 - 1. 搜索的用户如果不存在，返回[无此用户]
        // 前置条件 - 2. 搜索账号是你自己，返回[不能添加自己]
        // 前置条件 - 3. 搜索的朋友已经是你的好友，返回[该用户已经是你的好友]
        Integer status = userDao.preconditionSearchFriends(myUserId, friendUsername);
        if (status == SearchFriendsStatusEnum.SUCCESS.status) {
            userDao.sendFriendRequest(myUserId, friendUsername);
        } else {
            String errorMsg = SearchFriendsStatusEnum.getMsgByKey(status);
            return JSONResult.errorMsg(errorMsg);
        }

        return JSONResult.ok();
    }



    /**
     * Send friend request from from my user id to friend user name.
     *
     * @param userId id of current user
     * @return {@link JSONResult} message.
     * @throws Exception the exception
     */
    @PostMapping("/queryFriendRequests")
    public JSONResult queryFriendRequests(String userId) {

        // 0. 判断不能为空
        if (userId==null) {
            return JSONResult.errorMsg("");
        }

        // 1. 查询用户接受到的朋友申请
        return JSONResult.ok(userDao.queryFriendRequestList(userId));
    }


    /**
     * Send friend request from from my user id to friend user name.
     * @param acceptUserId the user id of accepter
     * @param sendUserId the user id of sender
     * @param operType 0 for decline;1 for accept
     * @return {@link JSONResult} message.
     * @throws Exception the exception
     */
    @PostMapping("/operFriendRequest")
    public JSONResult operFriendRequest(String acceptUserId, String sendUserId,
                                             Integer operType) {

        // 0. acceptUserId sendUserId operType 判断不能为空
        System.out.println(acceptUserId);
        System.out.println(sendUserId);
        System.out.println(operType);
        if (acceptUserId == null
                || sendUserId==null
                || operType == null) {
            return JSONResult.errorMsg("");
        }

        // 1. 如果operType 没有对应的枚举值，则直接抛出空错误信息
        if (OperatorFriendRequestTypeEnum.getMsgByType(operType)==null) {
            return JSONResult.errorMsg("");
        }

        if (operType == OperatorFriendRequestTypeEnum.IGNORE.type) {
            // 2. 判断如果忽略好友请求，则直接删除好友请求的数据库表记录
            userDao.deleteFriendRequest(sendUserId, acceptUserId);
        } else if (operType == OperatorFriendRequestTypeEnum.PASS.type) {
            // 3. 判断如果是通过好友请求，则互相增加好友记录到数据库对应的表
            // 然后删除好友请求的数据库表记录
            userDao.passFriendRequest(sendUserId, acceptUserId);
        }

        // 4. 数据库查询好友列表
        List<MyFriendsVO> myFirends = userDao.queryMyFriends(acceptUserId);

        return JSONResult.ok(myFirends);
    }

    @PostMapping("/myFriends")
    public JSONResult myFriends(String userId) {
        // 0. userId 判断不能为空
        if (userId == null) {
            return JSONResult.errorMsg("");
        }

        // 1. 数据库查询好友列表
        List<MyFriendsVO> myFirends = userDao.queryMyFriends(userId);

        return JSONResult.ok(myFirends);
    }

    /**
     *
     * @Description: 用户手机端获取未签收的消息列表
     */
    @PostMapping("/getUnReadMsgList")
    public JSONResult getUnReadMsgList(String acceptUserId) {
        // 0. userId 判断不能为空
        if (StringUtils.isBlank(acceptUserId)) {
            return JSONResult.errorMsg("");
        }

        // 查询列表
        List<ChatHistory> unreadMsgList = userDao.getUnReadMsgList(acceptUserId);

        return JSONResult.ok(unreadMsgList);
    }
}
