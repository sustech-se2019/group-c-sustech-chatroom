package sim.mapper;

import sim.pojo.Users;
import sim.utils.MyMapper;
import sim.pojo.vo.*;
import java.util.List;

public interface UsersMapperCustom extends MyMapper<Users> {
    public List<FriendRequestVO> queryFriendRequestList(String acceptUserId);

    public List<MyFriendsVO> queryMyFriends(String userId);

    public void batchUpdateMsgSigned(List<String> msgIdList);

}