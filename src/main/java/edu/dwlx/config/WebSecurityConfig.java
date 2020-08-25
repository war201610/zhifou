package edu.dwlx.config;

import edu.dwlx.services.UserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("zhangsan")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .roles("USER");
//        auth.userDetailsService(new UserService());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/zhifou/user/register", "/zhifou/user/login",
                            "/zhifou/user/register.html", "/css/**","/img/**","/js/**",
                            "/login", "/").permitAll()
                    .antMatchers("/zhifou/people/**", "/zhifou/know/**").hasAuthority("USER")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/zhifou/user/login.html")
                    .loginProcessingUrl("/zhifou/user/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
//                    .defaultSuccessUrl("/zhifou/know/index.html")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/zhifou/user/logout")
                    .logoutSuccessUrl("/zhifou/user/login.html")
                    .invalidateHttpSession(true);
        http.csrf().disable();
//                .deleteCookies("token_token")
             }
}