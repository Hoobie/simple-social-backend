package com.example.api.rest;

import com.example.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user/{userName}")
public class PostsController {

    @Autowired
    private PostsService postsService;

    @RequestMapping(path = "post", method = RequestMethod.POST)
    public String createPost(@PathVariable String userName, @RequestBody String message) {
        return postsService.createPost(userName, message).toString();
    }
}
