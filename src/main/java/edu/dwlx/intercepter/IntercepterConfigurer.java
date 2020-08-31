package edu.dwlx.intercepter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfigurer implements WebMvcConfigurer {

    @Autowired
    CustomIntercepter customIntercepter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customIntercepter)
                .addPathPatterns("/zhifou/people/**")
                .excludePathPatterns("/zhifou/people/**/info", "/zhifou/people/**/collections",
                        "/zhifou/people/**/asks", "/zhifou/people/**/answers", "/zhifou/people/**/questions",
                        "/zhifou/people/**/following", "/zhifou/people/**/followers");
    }
}
