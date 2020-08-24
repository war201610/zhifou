package edu.dwlx;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
class DwlxApplicationTests {
    private static final String user = "/zhifou/user";
    private static final String people = "/zhifou/people";

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @BeforeEach
    void initial() {
        System.out.println("initial");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void loginTest() throws Exception {
        String name = "zhangsan";
        String password = "123";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/zhifou/user/login?name=" + name + "&password=" + password))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/zhifou/know/index.html"));
    }
    @Test
    void registerTest() throws Exception {
        String name = "luolaoshi";
        String password = "123";
        mockMvc.perform(MockMvcRequestBuilders
                .get("/zhifou/user/register?name=" + name + "&password=" + password))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("/zhifou/user/login.html"))
                .andExpect(MockMvcResultMatchers.model().attribute("message", Matchers.is("注册成功")));
    }

    @Test
    public void personalHomepageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/zhifou/people/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("forward:/zhifou/people/personalHomepage.html"));
    }
    @Test
    public void newControllerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(user + "/login?username=zhanasdfgsan&password=123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("message", "用户不存在"))
                .andExpect(MockMvcResultMatchers.view().name("/zhifou/error/error.html"));
    }
    @Test
    public void newControllerTest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(user + "/register?name=zhangsan&password=123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("message", "用户名已存在"))
                .andExpect(MockMvcResultMatchers.view().name("/zhifou/error/error.html"));
    }
    @Test
    public void newControllerTest2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/zhifou/people/1/answers"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
