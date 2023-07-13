package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByFullName(String fullName) throws UserNotFoundException {
        return userRepository.findByFullName(fullName)
                .orElseThrow(() -> new UserNotFoundException(fullName));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) throws UserNotFoundException {
        if (!userRepository.existsByFullName(user.getFullName())) {
            throw new UserNotFoundException(user.getFullName());
        }
        return userRepository.save(user);
    }

    public void deleteUserById(int id) throws UserNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID: " + id + " not found");
        }
        userRepository.deleteById(id);
    }
    public Optional<User> loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public Optional<User> getLoggedInUser(String username) {
        return userRepository.findByUsername(username);
    }
}
