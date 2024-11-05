package com.example.UsersAPI.service;

import com.example.UsersAPI.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final List<User>  users = new ArrayList<User>();

    public UserService() {

        users.add(new User(1L, "João", "joao@email.com"));
        users.add(new User(2L, "Maria", "maria@email.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public boolean deleteUser(Long id) {

        Optional<User> user = getUserById(id);

        if (user.isPresent()) {
            users.remove(user.get());
            return true;
        }

        return false;
    }

    public Optional<User> updateUser(Long id, User updatedUser) {

        Optional<User> existingUser = getUserById(id);

        if (existingUser.isPresent()) {

            User user = existingUser.get();
            user.setNome(updatedUser.getNome());
            user.setEmail(updatedUser.getEmail());
            return Optional.of(user);
        }

        return Optional.empty();
    }
}

