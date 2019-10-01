package github.com.redisdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails admin =
        User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build();

    UserDetails user1 =
        User.withDefaultPasswordEncoder()
            .username("user1")
            .password("password")
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(admin, user1);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .httpBasic()
        .and()
        .formLogin()
        .defaultSuccessUrl("/hello")
        .and()
        .authorizeRequests()
        .antMatchers("/generate", "/get-direct")
        .permitAll()
        .antMatchers("/")
        .hasRole("ADMIN")
        .anyRequest()
        .authenticated()
                ;
//        .and()
//        .exceptionHandling()
//        .authenticationEntryPoint(
//            (request, response, authException) ->
//                response.sendError(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED!!"));
  }
}
