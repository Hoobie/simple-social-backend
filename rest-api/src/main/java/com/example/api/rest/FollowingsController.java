package com.example.api.rest;

import com.example.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/following")
public class FollowingsController {

    private final UsersService usersService;

    @Autowired
    public FollowingsController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> exceptionHandler(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "{followerName}/{followeeName}", method = RequestMethod.POST)
    public void follow(@PathVariable String followerName, @PathVariable String followeeName) {
        usersService.follow(followerName, followeeName);
    }
}
