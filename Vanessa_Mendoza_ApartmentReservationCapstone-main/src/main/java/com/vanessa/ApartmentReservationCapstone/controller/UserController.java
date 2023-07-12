package com.vanessa.ApartmentReservationCapstone.controller;

import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable String fullName, Model model) throws UserNotFoundException {
        User existingUser = userService.getUserByFullName(fullName);
        model.addAttribute("user", existingUser);
        return "userForm";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@ModelAttribute User user) throws UserNotFoundException {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable String fullName) throws UserNotFoundException {
        userService.deleteUser(fullName);
        return "redirect:/usesers";
    }

    @GetMapping("/register")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "userForm";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute User user) throws Exception {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user) {
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showUserProfile(Model model) {
        return "profile";
    }

    @GetMapping("/logout")
    public String logoutUser() {
        return "redirect:/login";
    }

}
