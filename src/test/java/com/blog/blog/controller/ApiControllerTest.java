package com.blog.blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deleteCategory() throws Exception {

        mockMvc.perform(get("/api/category/delete/POLITIKA").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string("successful deletion"))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testUser", authorities = {"USER", "ADMIN"})
    void getCategory() throws Exception {

        mockMvc.perform(get("/api/category/nemletezo").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andReturn();
    }



    @Test
    @WithMockUser(username = "testUser", authorities = {"USER", "ADMIN"})
    void testListCategory() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/category/list").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    @Test
    @WithMockUser(username = "testUser", authorities = {"USER", "ADMIN"})
    void listUser() throws Exception {

        mockMvc.perform(get("/api/user/list").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

    }

}