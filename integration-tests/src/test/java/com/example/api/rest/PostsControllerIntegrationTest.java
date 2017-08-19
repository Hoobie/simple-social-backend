package com.example.api.rest;

import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.PostsRepository;
import com.example.repositories.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostsControllerIntegrationTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldCreatePost() throws Exception {
        // when
        MockHttpServletResponse response = mockMvc.perform(post("/user/{userName}/post", "name").content("message"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        // then
        User user = usersRepository.read("name");
        assertThat(user)
                .isNotNull()
                .isEqualTo(new User("name"));

        Post post = postsRepository.read(UUID.fromString(response.getContentAsString()));
        assertThat(post.getMessage()).isEqualTo("message");
        assertThat(user.getPosts()).contains(post);
    }
}
