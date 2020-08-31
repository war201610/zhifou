package edu.dwlx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@MapperScan("edu.dwlx.mapper")
public class DwlxApplication {
    public static void main(String[] args) {
        SpringApplication.run(DwlxApplication.class, args);
    }
    @RequestMapping
    public String defaultMapping() {
        return "/zhifou/user/login.html";
    }
}
