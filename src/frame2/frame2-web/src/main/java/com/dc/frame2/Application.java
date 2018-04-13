import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/4/10.
 */
@SpringBootApplication
@ComponentScan("com.dc")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
