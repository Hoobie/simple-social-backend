package com.example.api.rest;

import com.example.entities.User;
import com.example.repositories.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FollowingsControllerIntegrationTest {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldFollow() throws Exception {
        // given
        String followerName = "user1";
        String followeeName = "user2";
        User follower = new User(followerName);
        usersRepository.create(followerName, follower);
        User followee = new User(followerName);
        usersRepository.create(followeeName, followee);

        // when
        mockMvc.perform(post("/following/{follower}/{followee}", followerName, followeeName))
                .andExpect(status().isOk());

        // then
        assertThat(follower.getFollowed()).contains(followee);
        assertThat(followee.getFollowed()).isEmpty();
    }

    @Test
    public void shouldNotFollowWhenUserDoesNotExist() throws Exception {
        // given
        String followerName = "user1";
        String followeeName = "user2";
        User follower = new User(followerName);
        usersRepository.create(followerName, follower);

        // when then
        mockMvc.perform(post("/following/{follower}/{followee}", followerName, followeeName))
                .andExpect(status().isBadRequest());
    }
}
