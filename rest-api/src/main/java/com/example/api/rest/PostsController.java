package com.example.api.rest;

import com.example.entities.Post;
import com.example.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/user/{userName}")
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @RequestMapping(path = "post", method = RequestMethod.POST)
    public String createPost(@PathVariable String userName, @RequestBody @Valid Post post) {
        return postsService.createPost(userName, post).toString();
    }

    @RequestMapping(path = "posts", method = RequestMethod.GET)
    public Iterable<Post> getWall(@PathVariable String userName) {
        return postsService.getWall(userName);
    }

    @RequestMapping(path = "timeline", method = RequestMethod.GET)
    public Iterable<Post> getTimeline(@PathVariable String userName) {
        return postsService.getTimeline(userName);
    }
}
