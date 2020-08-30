package edu.dwlx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

@SpringBootTest
@WebAppConfiguration
@MapperScan("edu.dwlx.mapper")
class DwlxApplicationTests {
    private static final String user = "/zhifou/user";
    private static final String people = "/zhifou/people";

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @BeforeEach
    public void initial() {
        System.out.println("initial");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void intercetperTest() throws Exception {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
                ("zhou", "123", Arrays.asList(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/zhifou/people/12/edit")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("uid","12")
                .param("gender","1")
                .param("introduction","12abc")
                .param("career","12")
                .param("industry","12")
                .param("email","12"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void agreeTest() {

    }
}
