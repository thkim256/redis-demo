package github.com.redisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class RedisDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(RedisDemoApplication.class, args);
  }
}
