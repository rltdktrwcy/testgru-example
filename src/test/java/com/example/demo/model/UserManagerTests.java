package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import com.example.demo.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class UserManagerTests {

    private UserManager userManager;

    @BeforeEach
    void setUp() {
        userManager = new UserManager();
    }

    @Test
    void testAddUser() {
        User user = userManager.addUser("John Doe", "john@example.com");

        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
    }

    @Test
    void testAddMultipleUsers() {
        User user1 = userManager.addUser("John", "john@example.com");
        User user2 = userManager.addUser("Jane", "jane@example.com");

        assertEquals(1, user1.getId());
        assertEquals(2, user2.getId());
        assertEquals(2, userManager.getAllUsers().size());
    }

    @Test
    void testFindUserById() {
        User addedUser = userManager.addUser("John", "john@example.com");
        Optional<User> foundUser = userManager.findUserById(addedUser.getId());

        assertTrue(foundUser.isPresent());
        assertEquals(addedUser.getId(), foundUser.get().getId());
        assertEquals(addedUser.getName(), foundUser.get().getName());
        assertEquals(addedUser.getEmail(), foundUser.get().getEmail());
    }

    @Test
    void testFindUserByIdNotFound() {
        Optional<User> user = userManager.findUserById(999);
        assertFalse(user.isPresent());
    }

    @Test
    void testDeleteUser() {
        User user = userManager.addUser("John", "john@example.com");
        boolean deleted = userManager.deleteUser(user.getId());

        assertTrue(deleted);
        assertTrue(userManager.getAllUsers().isEmpty());
    }

    @Test
    void testDeleteNonExistentUser() {
        boolean deleted = userManager.deleteUser(999);
        assertFalse(deleted);
    }

    @Test
    void testGetAllUsers() {
        userManager.addUser("John", "john@example.com");
        userManager.addUser("Jane", "jane@example.com");

        List<User> users = userManager.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("John", users.get(0).getName());
        assertEquals("Jane", users.get(1).getName());
    }

    @Test
    void testGetAllUsersEmptyList() {
        List<User> users = userManager.getAllUsers();
        assertTrue(users.isEmpty());
    }
}
