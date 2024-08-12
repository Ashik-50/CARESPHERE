package com.example.caresphere.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.caresphere.Model.User;
import com.example.caresphere.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User createUser(User user) {
        return repo.save(user);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return repo.findById(id);
    }

    public boolean deleteUser(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public long countUsers(){
        return repo.count();
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public boolean usernameExists(String username) {
        return repo.findByUsername(username) != null;
    }

    public boolean emailExists(String email) {
        return repo.findByEmail(email) != null;
    }
}
