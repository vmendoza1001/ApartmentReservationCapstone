package com.vanessa.ApartmentReservationCapstone.user;

import com.vanessa.ApartmentReservationCapstone.security.Role;
import com.vanessa.ApartmentReservationCapstone.security.User;
import com.vanessa.ApartmentReservationCapstone.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getAll(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        List<User> allUsers = userService.getAllUsers();
        for (User user: allUsers ) {
            for (Role role: user.getRoleSet() ){
                System.out.println(" print out here" + role.getName() );

            }

        }

        return "users";
    }



    @PostMapping("/saveUser/{id}")
    public String registerUserAccount(@PathVariable(value = "id") long id,
                                      @ModelAttribute("user") @Valid User user,
                                      BindingResult result){

        Logger logger = LoggerFactory.getLogger(UserController.class);

//        User existing = userService.findByEmail(userDto.getEmail());

        if (result.hasErrors()){
            return "redirect:/users";
        }

        userService.updateUser(user);

        logger.info("User with email " + user.getEmail() + " has been updated");

        return "redirect:/users";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // get user from the service
        User user = userService.getUserById(id);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable(value = "id") long id) {
        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/users";
    }

    @GetMapping("/updateRole/{id}")
    public String updateRole(@PathVariable(value = "id") long id) {
        // call delete user method
        this.userService.updateRole(id);
        return "redirect:/users";
    }


}
