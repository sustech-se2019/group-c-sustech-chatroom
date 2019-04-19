package sim.DaoImpl;

import org.junit.Assert;
import org.junit.Test;
import sim.pojo.Users;
import sun.usagetracker.UsageTrackerClient;

import java.util.Random;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void queryUsernameIsExistCorrect() {
        UserDaoImpl userDao = new UserDaoImpl();
        Assert.assertTrue(userDao.queryUsernameIsExist("test"));
    }

    @Test
    public void queryUsernameIsExistWrong() {
        UserDaoImpl userDao = new UserDaoImpl();
        Assert.assertTrue(userDao.queryUsernameIsExist("dgiwefdbiwebfi"));
    }

    @Test
    public void queryUserForLoginFail() {
        UserDaoImpl userDao = new UserDaoImpl();
        Assert.assertNotNull(userDao.queryUserForLogin("test","123456"));
    }


    @Test
    public void queryUserForLoginSuccess() {
        UserDaoImpl userDao = new UserDaoImpl();
        Assert.assertNotNull(userDao.queryUserForLogin("",""));
    }



    @Test
    public void createUser1() {
        UserDaoImpl userDao = new UserDaoImpl();
        Users user = new Users();
        user.setId("test");
        user.setUsername("test");
        user.setGpa(0.0);
        user.setNickname("test");
        user.setPassword("123456");
        try{
            userDao.createUser(user);
        }catch (Exception e){
            return;
        }
        Assert.fail();
    }

    @Test
    public void createUser2() {
        Random rnd = new Random();
        UserDaoImpl userDao = new UserDaoImpl();
        Users user = new Users();
        double num = rnd.nextDouble();
        user.setId("test" + num);
        user.setUsername("test"+ num);
        user.setGpa(0.0);
        user.setNickname("test" + num);
        user.setPassword("123456");
        userDao.createUser(user);
    }


    @Test
    public void updateUserInfo1() {
        UserDaoImpl userDao = new UserDaoImpl();
        Users user = userDao.queryUserById("test");
        Assert.assertEquals(user.getId(),userDao.updateUserInfo(user).getId());
    }

    @Test
    public void updateUserInfo2() {
        UserDaoImpl userDao = new UserDaoImpl();
        Users user = userDao.queryUserById("test");
        user.setNickname("666666");
        Assert.assertEquals(user.getNickname(),userDao.updateUserInfo(user).getNickname());
    }


    @Test
    public void queryUserById1() {
        UserDaoImpl userDao = new UserDaoImpl();
        Users user = userDao.queryUserById("test");
        Assert.assertNotNull(user);
    }

    @Test
    public void queryUserById2() {
        UserDaoImpl userDao = new UserDaoImpl();
        Users user = userDao.queryUserById("tesffefsfdsfdsdfst");
        Assert.assertNull(user);
    }
}