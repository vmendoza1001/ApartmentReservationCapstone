package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User updateUser(User user) throws UserNotFoundException {
        if (!userRepository.existsByFullName(user.getFullName())) {
            throw new UserNotFoundException(user.getFullName());
        }
        return userRepository.save(user);
    }

    public void deleteUser(String fullName) throws UserNotFoundException {
        if (!userRepository.existsByFullName(fullName)) {
            throw new UserNotFoundException(fullName);
        }
        userRepository.deleteByFullName(fullName);
    }
}
