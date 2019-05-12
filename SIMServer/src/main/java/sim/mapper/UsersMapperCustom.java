package sim.mapper;

import java.util.List;

import sim.pojo.Users;
import sim.utils.MyMapper;

public interface UsersMapperCustom extends MyMapper<Users> {
	
	public void batchUpdateMsgSigned(List<String> msgIdList);
	
}