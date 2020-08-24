package edu.dwlx.config;

import edu.dwlx.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                            "/zhifou/user/register.html").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/zhifou/user/login.html")
                    .loginProcessingUrl("/zhifou/user/login")
//                    .defaultSuccessUrl("/zhifou/know/index.html")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/zhifou/user/logout")
                    .logoutSuccessUrl("/zhifou/user/login.html")
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//                    }
//                })
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//
//                    }
//                })
                    .invalidateHttpSession(true);
        http.csrf().disable();
//                .deleteCookies("token_token")
             }
}