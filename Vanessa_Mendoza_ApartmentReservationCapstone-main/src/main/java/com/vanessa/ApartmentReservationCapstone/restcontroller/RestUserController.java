//package com.vanessa.ApartmentReservationCapstone.restcontroller;
//
//import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
//import com.vanessa.ApartmentReservationCapstone.model.User;
//import com.vanessa.ApartmentReservationCapstone.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/users")
//public class RestUserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/users")
//    public User createUser(@RequestBody User user) throws Exception {
//        return userService.createUser(user);
//    }
//
//    @GetMapping("/users/{id}")
//    public User getUser(@PathVariable String fullName) throws UserNotFoundException {
//        return userService.getUserByFullName(fullName);
//    }
//
//    @PutMapping("/users")
//    public User updateUser(@RequestBody User user) throws UserNotFoundException {
//        return userService.updateUser(user);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public void deleteUser(@PathVariable String fullName) throws UserNotFoundException {
//        userService.deleteUser(fullName);
//    }
//}
