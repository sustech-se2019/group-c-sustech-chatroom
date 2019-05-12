package sim.DaoImpl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sim.enums.MsgSignFlagEnum;
import sim.mapper.ChatHistoryMapper;
import sim.mapper.UsersMapper;
import sim.mapper.UsersMapperCustom;
import sim.netty.ChatMsg;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import sim.Dao.UserDao;
import sim.pojo.Users;
import sim.pojo.ChatHistory;

import java.util.Date;
import java.util.List;

/**
 * This class is to implement UserDao interface.
 */
@Service
public class UserDaoImpl implements UserDao {

    @Autowired(required = false)
    private UsersMapper userMapper;
    @Autowired(required = false)
    private ChatHistoryMapper chatHistoryMapper;
    @Autowired(required = false)
    private UsersMapperCustom usersMapperCustom;
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

    /**
     * @param chatMsg
     * @Description: 保存聊天消息到数据库
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveMsg(ChatMsg chatMsg) {

        ChatHistory msgDB = new ChatHistory();
        String msgId = Sid.nextShort();
        msgDB.setMsgId(msgId);
        msgDB.setToId(chatMsg.getReceiverId());
        msgDB.setFromId(chatMsg.getSenderId());
        msgDB.setSendTime(new Date());
        msgDB.setSignFlag(MsgSignFlagEnum.unsign.type);
        msgDB.setMsg(chatMsg.getMsg());

        chatHistoryMapper.insert(msgDB);

        return msgId;
    }

    /**
     * @param msgIdList
     * @Description: 批量签收消息
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateMsgSigned(List<String> msgIdList) {
        usersMapperCustom.batchUpdateMsgSigned(msgIdList);
    }

    /**
     * @param toId
     * @Description: 获取未签收消息列表
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ChatHistory> getUnReadMsgList(String toId) {

        Example chatExample = new Example(ChatHistory.class);
        Criteria chatCriteria = chatExample.createCriteria();
        chatCriteria.andEqualTo("signFlag", 0);
        chatCriteria.andEqualTo("toId", toId);

        List<ChatHistory> result = chatHistoryMapper.selectByExample(chatExample);

        return result;
    }
}
