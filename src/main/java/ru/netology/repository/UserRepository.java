package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.entity.UserEntity;
import ru.netology.util.Authorities;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    Map<UserEntity, List<Authorities>> authoritiesMap = Map.of(
            new UserEntity("admin", "admin"),
            List.of(Authorities.WRITE, Authorities.DELETE, Authorities.READ)
    );

    public List<Authorities> getUserAuthorities(UserEntity user) {
        return authoritiesMap.getOrDefault(user, List.of());
    }
}
