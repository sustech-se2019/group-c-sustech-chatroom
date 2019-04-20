package sim.Controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sim.Dao.UserDao;
import sim.pojo.Users;
import sim.pojo.vo.UsersVO;
import sim.utils.JSONResult;
import sim.utils.MD5Utils;
import org.n3r.idworker.Sid;
/**
 * The Login and regist controller,witch is used to reply the request and update server.
 */
@RestController
public class LoginAndRegistController {

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
        if (userResult != null) {

            UsersVO userVO = new UsersVO();
            BeanUtils.copyProperties(userResult, userVO);

            return JSONResult.ok(userVO);
        }else{
            return JSONResult.errorMsg("用户名或密码不正确...");
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
        if (!userExit) {
            user.setId(Sid.nextShort());
            user.setNickname(user.getUsername());
            Users userResult = userDao.createUser(user);
            if (userResult != null){
                UsersVO userVO = new UsersVO();
                BeanUtils.copyProperties(userResult, userVO);

                return JSONResult.ok(userVO);
            }else{
                return JSONResult.errorMsg("创建用户失败...");
            }

        }else{
            return JSONResult.errorMsg("用户已存在...");
        }

    }
}
