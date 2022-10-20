package com.example.demogame.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @CrossOrigin
    @PostMapping("/insert")
    public User addUser(@RequestBody User userForInsert){
        return userService.addUser(userForInsert);
    }
}
