package com.example.demo.model;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserManager {
    private List<User> users = new ArrayList<>();
    private int nextId = 1;

    public User addUser(String name, String email) {
        User user = User.builder().id(nextId++).name(name).email(email).build();
        this.users.add(user);
        return user;
    }

    public Optional<User> findUserById(int id) {
        return this.users.stream().filter(user -> user.getId() == id).findFirst();
    }

    public boolean deleteUser(int id) {
        return this.users.removeIf(user -> user.getId() == id);
    }

    public List<User> getAllUsers() {
        return this.users;
    }
}
