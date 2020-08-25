package edu.dwlx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ModelResultMatchers;
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
    public void initial() {
        System.out.println("initial");
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

//    @Test
//    public void personalInfoTest() {
//        mockMvc.perform(MockMvcRequestBuilders.get("/zhifou/people/1/info"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.)
//    }
}
