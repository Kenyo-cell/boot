package ru.netology.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.entity.UserEntity;
import ru.netology.exception.InvalidCredentialsException;
import ru.netology.exception.UnauthorizedUserException;
import ru.netology.service.AuthorizationService;
import ru.netology.util.Authorities;
import ru.netology.util.User;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuthorizationController {
    @Autowired
    AuthorizationService authorizationService;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid @User UserEntity user, BindingResult result) {
        if (result.hasErrors())
            System.out.println("Has Errors");
        return authorizationService.getAuthorities(user.getUsername(), user.getPassword());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentialsException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnauthorizedUserException.class})
    public ResponseEntity<String> handleUnauthorizedUserException(UnauthorizedUserException e) {
        System.out.println(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
