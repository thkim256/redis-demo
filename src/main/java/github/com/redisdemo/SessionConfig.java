package github.com.redisdemo;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.RedisSessionProperties;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

@Configuration
@Import(RedisSessionProperties.class)
public class SessionConfig {
  @Configuration
  class DisableRedisHttpSessionConfiguration extends RedisHttpSessionConfiguration {
    @Autowired
    public void customize(SessionProperties sessionProperties, RedisSessionProperties redisSessionProperties) {
      Duration timeout = sessionProperties.getTimeout();

      if (timeout != null) {
        setMaxInactiveIntervalInSeconds((int) timeout.getSeconds());
      }
      setRedisNamespace(redisSessionProperties.getNamespace());
      setRedisFlushMode(redisSessionProperties.getFlushMode());
      setCleanupCron(redisSessionProperties.getCleanupCron());

//      setConfigureRedisAction(ConfigureRedisAction.NO_OP);
    }

    @Override
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
      return new RedisMessageListenerContainer();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
      // ignore
    }
  }

}
