package ru.netology.profile;

public interface SystemProfile {
    default String getProfile() {
        return this.getClass().getSimpleName();
    }
}
