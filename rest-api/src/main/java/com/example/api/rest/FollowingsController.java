package com.example.api.rest;

import com.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/following")
public class FollowingsController {

    private final UsersService usersService;

    @Autowired
    public FollowingsController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "{followerName}/{followeeName}", method = RequestMethod.POST)
    public void follow(@PathVariable String followerName, @PathVariable String followeeName) {
        usersService.follow(followerName, followeeName);
    }
}
