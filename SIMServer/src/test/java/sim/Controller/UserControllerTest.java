package sim.Controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;
public class UserControllerTest {

    private static String ServerUrl = "http://127.0.0.1:8081/";

    private static void login(String name, String pwd){
        JSONObject data = new JSONObject();
        data.put("username",name);
        data.put("password",pwd);
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"login");
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    private static void regist(String name, String pwd, double gpa){

        JSONObject data = new JSONObject();
        data.put("username",name);
        data.put("password",pwd);
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"regist");
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    private static void getFriendList(String userid){
        JSONObject data = new JSONObject();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"/myFriends?userId=0");
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    private static void postFriendRequest(String myUserId, String friendUserName){
        JSONObject data = new JSONObject();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"/addFriendRequest?myUserId=0&friendUsername=matt");
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    private static void pullFriendRequest(String userid){
        JSONObject data = new JSONObject();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"/queryFriendRequests?userId="+userid);
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    private static void acceptFriendRequest(String acceptUserId, String sendUserId){
        JSONObject data = new JSONObject();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"/operFriendRequest?acceptUserId="+acceptUserId
                +"&sendUserId="+sendUserId+"&operType=1");
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    public void findFriends(String myUserId, String friendUsername){
        JSONObject data = new JSONObject();
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"/search?myUserId="+myUserId
                +"&friendUsername="+friendUsername);
        String response = null;
        try {
            StringEntity s = new StringEntity(data.toJSONString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                response = EntityUtils.toString(res.getEntity());// 返回json格式：
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    @Test
    public void testSearchUser1(){
        findFriends("0","matt");
    }

    @Test
    public void testSearchUser2(){
        findFriends("0","test");
    }

    @Test
    public void testSearchUser3(){
        findFriends("0","123");
    }


    @Test
    public void testOperFriendRequest(){
        acceptFriendRequest("1904229Z629M05WH","0");
    }


    @Test
    public void testAddFriendRequest(){
        postFriendRequest("0","matt");
    }

    @Test
    public void testQueryFriendRequest(){
        pullFriendRequest("1904229Z629M05WH");
    }


    @Test
    public void testMyFriends1(){
        getFriendList("0");
    }

    @Test
    public void testMyFriends2(){
        getFriendList("190416G2316A6NC0");
    }

    @Test
    public void testMyFriends3(){
        getFriendList("1904170PA8BXPACH");
    }


    @Test
    public void testLogin1() {
        login("123","123");
    }

    @Test
    public void testLogin2() {
        login("123fy3298ry923rf","123u390ry892rgff");
    }

    @Test
    public void testRegist2(){
        regist("123","123",0);
    }

    @Test
    public void testRegist3(){
        regist("uhdiwei","uhihii", 3.4);
    }

    @Test
    public void getUnReadMsgListTest1(){
        String url = ServerUrl + "/getUnReadMsgList?acceptUserId=1";
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String response = null;
        try {
            StringEntity s = new StringEntity("");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            Assert.assertEquals(200,res.getStatusLine().getStatusCode());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getUnReadMsgListTest2(){
        String url = ServerUrl + "/getUnReadMsgList?acceptUserId=";
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String response = null;
        try {
            StringEntity s = new StringEntity("");
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            response = EntityUtils.toString(res.getEntity());
            System.out.println(response);
            Assert.assertEquals("{\"status\":500,\"msg\":\"\",\"data\":null,\"ok\":false}",response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}