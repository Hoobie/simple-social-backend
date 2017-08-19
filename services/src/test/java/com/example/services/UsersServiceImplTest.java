package com.example.services;

import com.example.entities.User;
import com.example.repositories.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    private UsersService usersService;

    @Before
    public void setUp() throws Exception {
        usersService = new UsersServiceImpl(usersRepository);
    }

    @Test
    public void shouldFollow() throws Exception {
        // given
        String followerName = "follower";
        User follower = new User(followerName);
        String followeeName = "followee";
        User followee = new User(followeeName);
        when(usersRepository.read(followerName)).thenReturn(follower);
        when(usersRepository.read(followeeName)).thenReturn(followee);

        // when
        usersService.follow(followerName, followeeName);

        // then
        assertThat(follower.getFollowed()).contains(followee);
        assertThat(followee.getFollowed()).isEmpty();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFollowWhenFollowerDoesNotExist() throws Exception {
        // given
        String followerName = "follower";
        String followeeName = "followee";
        User followee = new User(followeeName);
        when(usersRepository.read(followerName)).thenReturn(null);
        when(usersRepository.read(followeeName)).thenReturn(followee);

        // when
        usersService.follow(followerName, followeeName);

        // then
        // exception
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFollowWhenFolloweeDoesNotExist() throws Exception {
        // given
        String followerName = "follower";
        User follower = new User(followerName);
        String followeeName = "followee";
        when(usersRepository.read(followerName)).thenReturn(follower);
        when(usersRepository.read(followeeName)).thenReturn(null);

        // when
        usersService.follow(followerName, followeeName);

        // then
        // exception
    }
}