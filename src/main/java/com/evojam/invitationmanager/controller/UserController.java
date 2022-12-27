package com.evojam.invitationmanager.controller;

import com.evojam.invitationmanager.domain.User;
import com.evojam.invitationmanager.dto.UserDTO;
import com.evojam.invitationmanager.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user", produces = { "application/json; charset=utf-8" })
@CrossOrigin
@ApiOperation("User API")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody @Valid UserDTO user) {
        userService.createUser(user);
    }
}
