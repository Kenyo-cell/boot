package ru.netology.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.profile.SystemProfile;

@RestController
@RequestMapping("/")
//@ConfigurationProperties("system")
public class SystemProfileController {
    private final SystemProfile profile;

    public SystemProfileController(SystemProfile profile) {
        this.profile = profile;
    }

    @GetMapping
    public String getProfile() {
        return profile.getProfile();
    }
}
