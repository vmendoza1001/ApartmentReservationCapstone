package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(new User("Baily44", "Kangaroo6", "Baily Simmons", "456 9th Avenue, Brooklyn, NY 09857",
                "532-987-8977", "baily@email.com"));
        users.add(new User("Janice89", "Pyjamas880", "Janice Guerra", "879 Darby Street, Denver, Colorado 90998",
                "780-897-8977", "janice@email.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("Baily Simmons", result.get(0).getFullName());
        assertEquals("Janice Guerra", result.get(1).getFullName());
    }

    @Test
    public void testGetUserByFullName_ValidFullName_ReturnsUser() {
        // Arrange
        String fullName = "Baily Simmons";
        User user = new User("Baily44", "Kangaroo6", "Baily Simmons", "456 9th Avenue, Brooklyn, NY 09857",
                "532-987-8977", "baily@email.com");
        when(userRepository.findByFullName(fullName)).thenReturn(Optional.of(user));

        User result = userService.getUserByFullName(fullName);

        assertNotNull(result);
        assertEquals(fullName, result.getFullName());
    }

    @Test
    public void testGetUserByFullName_NonExistingFullName_ThrowsUserNotFoundException() {
        String fullName = "Non Existing User";
        when(userRepository.findByFullName(fullName)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserByFullName(fullName));
    }

    @Test
    public void testCreateUser_ValidUser_ReturnsCreatedUser() {
        User user = new User("Baily44", "Kangaroo6", "Baily Simmons", "456 9th Avenue, Brooklyn, NY 09857",
                "532-987-8977", "baily@email.com");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
        assertEquals("Baily Simmons", result.getFullName());
        assertEquals("baily@email.com", result.getEmailAddress());
    }

    @Test
    public void testUpdateUser_ExistingUser_ReturnsUpdatedUser() throws UserNotFoundException {
        User user = new User("Baily44", "Kangaroo6", "Baily Simmons", "456 9th Avenue, Brooklyn, NY 09857",
                "532-987-8977", "baily@email.com");
        when(userRepository.existsByFullName(user.getFullName())).thenReturn(true);
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals("Baily Simmons", result.getFullName());
        assertEquals("baily@email.com", result.getEmailAddress());
    }

    @Test
    public void testUpdateUser_NonExistingUser_ThrowsUserNotFoundException() {
        // Arrange
        User user = new User("Dale99", "Kangaroo6", "Dale Carnegie", "456 9th Avenue, Brooklyn, NY 09857",
                "532-987-8977", "dale@email.com");
        when(userRepository.existsByFullName(user.getFullName())).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user));
    }

    @Test
    public void testDeleteUser_NonExistingUser_ThrowsUserNotFoundException() {
        String fullName = "Non Existing User";
        when(userRepository.existsByFullName(fullName)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(fullName));
    }

}