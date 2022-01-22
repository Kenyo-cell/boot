package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.service.AuthorizationService;
import ru.netology.util.Authorities;

import java.util.List;

@RestController
public class AuthorizationController {
    @Autowired
    AuthorizationService authorizationService;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("username") String username,
                                            @RequestParam("password") String password) {
        return authorizationService.getAuthorities(username, password);
    }
}
