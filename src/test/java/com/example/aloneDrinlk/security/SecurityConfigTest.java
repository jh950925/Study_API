//package com.example.aloneDrinlk.security;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(SpringExtension.class) // Mockito 확장 등록
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SecurityConfigTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Test
//    @WithMockUser
//    public void testSecuredEndpointWithAuthenticatedUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/secured"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//        verify(jwtTokenProvider, never()).validateToken(anyString());
//    }
//
//    @Test
//    public void testSecuredEndpointWithoutAuthenticatedUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/secured"))
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//        verify(jwtTokenProvider, never()).validateToken(anyString());
//    }
//}
