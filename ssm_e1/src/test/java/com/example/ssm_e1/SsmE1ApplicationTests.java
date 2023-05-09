package com.example.ssm_e1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SsmE1ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            when calling hello endpoint authentication we should get back hello as response body and status of 200 OK
            """)
    @WithMockUser  // creates a mock security context with user inside
    public void testHelloAuthenticated() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    @DisplayName("""
            when calling hello endpoint authentication we should get back hello as response body and status of 401 UnAuthorized
            """)
    public void testHelloUnAuthenticated() throws Exception {
        mockMvc.perform(get("/hello")).andExpect(status().isUnauthorized());
    }
}
