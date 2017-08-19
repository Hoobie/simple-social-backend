package com.example.services;

import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.PostsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostsServiceImplTest {

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private UsersService usersService;

    private PostsService postsService;

    @Before
    public void setUp() throws Exception {
        postsService = new PostsServiceImpl(postsRepository, usersService);
    }

    @Test
    public void shouldCreatePost() throws Exception {
        // given
        User user = new User("name");
        Post post = new Post("message");
        when(usersService.createIfNotExists("name")).thenReturn(user);

        // when
        UUID id = postsService.createPost("name", post);

        // then
        verify(usersService).createIfNotExists("name");
        verify(postsRepository).create(any(UUID.class), any(Post.class));

        assertThat(id).isNotNull();
        assertThat(user.getPosts()).hasSize(1);

        assertThat(post.getMessage()).isEqualTo("message");
        assertThat(post.getDate().isBefore(OffsetDateTime.now())).isTrue();
    }
}