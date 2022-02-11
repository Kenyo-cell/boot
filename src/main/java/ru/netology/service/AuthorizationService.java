package ru.netology.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.entity.UserEntity;
import ru.netology.exception.InvalidCredentialsException;
import ru.netology.exception.UnauthorizedUserException;
import ru.netology.repository.UserRepository;
import ru.netology.util.Authorities;

import java.util.List;

@Service
public class AuthorizationService {
    @Autowired
    private UserRepository userRepository;

    public List<Authorities> getAuthorities(String username, String password) {
        if (isEmpty(username) || isEmpty(password))
            throw new InvalidCredentialsException("Username or password is empty");

        UserEntity user = new UserEntity(username, password);

        var userAuthorities = userRepository.getUserAuthorities(user);

        if (isEmpty(userAuthorities))
            throw new UnauthorizedUserException("Unknown user %s need Auth".formatted(username));

        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}
