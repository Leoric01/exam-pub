package com.urban.exampub.services;

import com.jayway.jsonpath.JsonPath;
import com.urban.exampub.models.User;
import com.urban.exampub.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    //works when I cancel auth in security filter chain, ain't sure how to implement basic auth into test
    @Test
    void getAllUserDto() throws Exception {
        List<User> testUsers = new ArrayList<>();
        testUsers.add(new User("user1", true, 20, "ccc", "USER"));
        testUsers.add(new User("user2",false, 0, "ccc", "ADMIN"));
        when(userService.getAllUser()).thenReturn(testUsers);
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(mvcResult -> {
                    String content = mvcResult.getResponse().getContentAsString();
                    List<Object> responseUsers = JsonPath.read(content, "$[*]");
                    assertEquals(2, responseUsers.size());
                    assertEquals(1, (Long) JsonPath.read(content, "$[0].id"));
                    assertEquals("user1", JsonPath.read(content, "$[0].name"));
                    assertEquals(20.0, JsonPath.read(content, "$[0].pocket"));
                    assertEquals(false, JsonPath.read(content, "$[0].active"));
                    assertEquals(true, JsonPath.read(content, "$[0].adult"));
                    assertEquals(2, (Long) JsonPath.read(content, "$[1].id"));
                    assertEquals("user2", JsonPath.read(content, "$[1].name"));
                    assertEquals(0.0, JsonPath.read(content, "$[1].pocket"));
                    assertEquals(false, JsonPath.read(content, "$[1].active"));
                    assertEquals(false, JsonPath.read(content, "$[1].adult"));
                });
      }

    @Test
    void getAllUser() {
        List<User> testUsers = new ArrayList<>();
        testUsers.add(new User("user1", true, 20, "ccc", "USER"));
        testUsers.add(new User("user2",false, 0, "ccc", "ADMIN"));
        when(userRepository.findAll()).thenReturn(testUsers);
        List<User> result = userService.getAllUser();
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getName());
        assertTrue(result.get(0).isAdult());
        assertEquals(20, result.get(0).getPocket());
        assertEquals("ccc", result.get(0).getPassword());
        assertEquals("USER", result.get(0).getRoles());
        assertEquals("user2", result.get(1).getName());
        assertFalse(result.get(1).isAdult());
        assertEquals(0, result.get(1).getPocket());
        assertEquals("ccc", result.get(1).getPassword());
        assertEquals("ADMIN", result.get(1).getRoles());

    }

    @Test
    void getUserDetail() {
      }

    @Test
    void createUser() {
      }

    @Test
    void loadUserByUsername() {
      }
}