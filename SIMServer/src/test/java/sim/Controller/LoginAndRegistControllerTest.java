package sim.Controller;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginAndRegistControllerTest {

    static String ServerUrl = "http://127.0.0.1:8081/";

    public static void login(String name, String pwd){
        String body = "{\"username\":" + name + ",\"password\":" + pwd + "}";
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"login");
        String response = null;
        try {
            StringEntity s = new StringEntity(body);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
    }

    public static void regist(String name, String pwd, double gpa){
        String body = "{\"username\":" + name + ",\"password\":" + pwd + ",\"gpa\":" + gpa + "}";
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(ServerUrl+"regist");
        String response = null;
        try {
            StringEntity s = new StringEntity(body);
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == 200){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = result;
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