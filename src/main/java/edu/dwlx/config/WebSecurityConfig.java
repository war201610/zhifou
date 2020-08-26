package edu.dwlx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/zhifou/user/logout")
                    .logoutSuccessUrl("/zhifou/user/login.html")
                    .invalidateHttpSession(true);
        http.csrf().disable();
             }
}