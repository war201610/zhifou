package edu.dwlx;

import edu.dwlx.controller.hot.HotController;
import edu.dwlx.entity.Answer;
import edu.dwlx.entity.User;
import edu.dwlx.services.UserService;
import org.checkerframework.checker.units.qual.A;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@WebAppConfiguration
@MapperScan("edu.dwlx.mapper")
class DwlxApplicationTests {
    private static final String user = "/zhifou/user";
    private static final String people = "/zhifou/people";

    @Autowired
    UserService userService;
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
    public void abc() throws Exception {
        User user = userService.searchUserByName("zhou");
        mockMvc.perform(MockMvcRequestBuilders.post("/zhifou/user/login")
                .param("username", "zhou")
                .param("password", "123")
        ).andExpect(MockMvcResultMatchers.view().name("redirect:/zhifou/know/know.html"));
    }

//    @Test
//    public void agreeTest() {
//        mockMvc.perform(MockMvcRequestBuilders.post("/zhifou/agree")
//                .param("kind", "answer")
//                .param("qid", "52")
//                .param("uid", "13")
//                .param("aid", "1")
//                .param("cid", "0"))
//                .andExpect()
//        );
//    }

    @Test
    public void zswTest(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/zhifou/hot/getHot"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List list = hotController.getHot();
//        System.out.println(list.toString());
    }

}
