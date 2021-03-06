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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Test
    public void shouldAddPostsInReverseOrder() throws Exception {
        // given
        String userName = "name";
        User user = new User(userName);
        when(usersService.createIfNotExists(userName)).thenReturn(user);

        // when
        postsService.createPost(userName, new Post("message1"));
        postsService.createPost(userName, new Post("message2"));
        postsService.createPost(userName, new Post("message3"));

        // then
        List<Post> posts = user.getPosts();
        assertThat(posts).hasSize(3);
        assertThat(posts.get(0).getMessage()).isEqualTo("message3");
        assertThat(posts.get(1).getMessage()).isEqualTo("message2");
        assertThat(posts.get(2).getMessage()).isEqualTo("message1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnWallWhenUserDoesNotExist() throws Exception {
        // given
        when(usersService.findByName("name")).thenReturn(Optional.empty());

        // when
        postsService.getWall("name");

        // then
        // exception
    }

    @Test
    public void shouldReturnTimeline() throws Exception {
        // given
        String userName1 = "name1";
        String userName2 = "name2";
        String userName3 = "name2";
        User user1 = new User(userName1);
        User user2 = new User(userName2);
        User user3 = new User(userName3);
        user1.getFollowed().add(user2);
        user1.getFollowed().add(user3);

        Post post0 = new Post("test0");
        Post post1 = new Post("test1");
        Post post2 = new Post("test2");
        post2.setDate(OffsetDateTime.now().plusMinutes(1));
        Post post3 = new Post("test3");
        post3.setDate(OffsetDateTime.now().plusMinutes(2));
        Post post4 = new Post("test4");
        post4.setDate(OffsetDateTime.now().plusMinutes(3));
        user1.getPosts().add(post0);
        user2.getPosts().add(post1);
        user3.getPosts().add(post2);
        user2.getPosts().add(post3);
        user3.getPosts().add(post4);
        when(usersService.findByName(userName1)).thenReturn(Optional.of(user1));

        // when
        List<Post> posts = new ArrayList<>(postsService.getTimeline(userName1));

        // then
        assertThat(posts).containsExactly(post4, post3, post2, post1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotReturnTimelineWhenUserDoesNotExist() throws Exception {
        // given
        when(usersService.findByName("name")).thenReturn(Optional.empty());

        // when
        postsService.getTimeline("name");

        // then
        // exception
    }
}