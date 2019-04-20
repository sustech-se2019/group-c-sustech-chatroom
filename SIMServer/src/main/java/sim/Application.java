package sim;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;
/**
 * To run the application.
 */
@SpringBootApplication
// 扫描mybatis mapper包路径
@MapperScan(basePackages="sim.mapper")
@ComponentScan(basePackages= {"sim"})
public class Application {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
    
}
