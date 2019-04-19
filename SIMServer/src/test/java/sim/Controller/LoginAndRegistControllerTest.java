package sim.Controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class LoginAndRegistControllerTest {

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


}