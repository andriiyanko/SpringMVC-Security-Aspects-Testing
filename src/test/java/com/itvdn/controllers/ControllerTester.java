package com.itvdn.controllers;

import com.andy.model.User;
import com.andy.persistence.model.Employee;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/app-simple-ctx.xml"})
@WebAppConfiguration
@FixMethodOrder
public class ControllerTester {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test(expected = NoSuchBeanDefinitionException.class)
    public void testContext() {
        Assert.assertNull((wac.getBean("beanForTest")));
    }

    @Test
    public void testController() throws Exception {
        mockMvc.perform(get("/bye2")).andDo(print()).andExpect(view().name("bye.jsp"));
    }

    @Test
    public void testController2() throws Exception {
        mockMvc.perform(get("/bye2")).andExpect(status().isOk()).andExpect(view().name("bye.jsp"));
    }

    @Test
    public void testController3() throws Exception {
        mockMvc.perform(get("/path/{userName}", "Grisha"))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testController4() throws Exception {
        mockMvc.perform(get("/rest/{userName}", "Oleg"))
                .andExpect(status().is2xxSuccessful());
        Assert.assertTrue(mockMvc.perform(get("/rest/{userName}", "Oleg"))
                .andReturn().getResponse().getContentAsString().contains("Ole"));
    }

    @Test
    public void testController5() throws Exception {
        mockMvc.perform(post("/pass-data").flashAttr("user", new User("Vasily", "Yanko", 35)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("forward:summary.jsp"))
                .andDo(print());
    }

    @Test
    public void testController6() throws Exception {
        mockMvc.perform(post("/pass-data").param("name", "Vasily").param("surname", "Yanko")
                .param("years", "35").flashAttr("user", new User()))
                .andExpect(status().is2xxSuccessful()).andExpect(forwardedUrl("summary.jsp"))
                .andExpect(view().name("forward:summary.jsp"))
                .andDo(print());
    }

    @Test
    public void testController7() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testController8() throws Exception {
        mockMvc.perform(get("/all")).andExpect(status().is2xxSuccessful());
    }

    /*Integration*/
    @Test
    public void testController9() throws Exception {
        mockMvc.perform(post("/employee/add").param("name", "Oleg")
        .param("position", "CEO").param("phone", "042")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testController10() throws Exception {
        System.out.println(mockMvc.perform(post("/employee/findByNameAndPosition").param("name", "Oleg")
                .param("position", "CEO")).andExpect(status().is2xxSuccessful()).andReturn().getModelAndView().getModel().get("employees"));
    }




    @Test
    public void testController11() throws Exception {
        System.out.println(mockMvc.perform(post("/employee/findByName").param("name", "Oleg"))
                .andExpect(status().is2xxSuccessful()).andReturn().getModelAndView().getModel().get("employees"));
    }

    @Test
    public void testController12() throws Exception {
        Object employee = mockMvc.perform(post("/employee/findByNameAndPhone").param("name", "Oleg")
                .param("phone", "042")).andExpect(status().is2xxSuccessful()).andReturn().getModelAndView().getModel().get("employees");

        if (employee instanceof List && !((List) employee).isEmpty()) {
            Optional<Employee> optEmp = ((List<Employee>) employee).stream().findFirst();
            if (optEmp.isPresent()) {
                Employee emp = optEmp.get();
                System.out.println(emp);
                mockMvc.perform(get("/employee/remove/{id}", emp.getId())).andExpect(status().is3xxRedirection());
            } else {
                System.out.println("No such employee");
            }
        }
    }
}
