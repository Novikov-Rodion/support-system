package com.example.support_system.service;

import com.example.support_system.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final Map<Long, User> users = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public User createUser(User user) {
        Long id = idCounter.getAndIncrement();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public Optional<User> updateUser(Long id, User userDetails) {
        if (users.containsKey(id)) {
            User existingUser = users.get(id);
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhone(userDetails.getPhone());
            return Optional.of(existingUser);
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        return users.remove(id) != null;
    }
}