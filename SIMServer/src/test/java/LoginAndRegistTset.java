
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class LoginAndRegistTset{

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
    public void test1(){
        login("123","123");
    }

    @Test
    public void test2(){
        regist("123","123",0);
    }

    @Test
    public void test3(){
        regist("456","456", 3.4);
    }
}

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes={Application.class})// 指定启动类
//public class LoginAndRegistTset {
//
//    @Test
//    public void testOne(){
//        System.out.println("test hello 1");
//    }
//
//    @Test
//    public void testTwo(){
//        System.out.println("test hello 2");
//        TestCase.assertEquals(1, 1);
//    }
//
//    @Before
//    public void testBefore(){
//        System.out.println("before");
//    }
//
//    @After
//    public void testAfter(){
//        System.out.println("after");
//    }
//
//}
