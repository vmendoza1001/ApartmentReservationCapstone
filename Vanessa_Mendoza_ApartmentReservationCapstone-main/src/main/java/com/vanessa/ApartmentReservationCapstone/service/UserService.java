package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) throws Exception {
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new Exception("Username already exists");
        }
        return userRepository.save(user);
    }

    public User getUser(Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User updateUser(User user) throws UserNotFoundException {
        if(userRepository.findById(user.getId()).isEmpty()){
            throw new UserNotFoundException(user.getId());
        }
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) throws UserNotFoundException {
        if(userRepository.findById(id).isEmpty()){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }
}
