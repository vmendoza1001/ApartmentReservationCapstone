package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @Test
    void createUser_ValidUser_ReturnsCreatedUser() throws Exception {
        // Arrange
        User user = new User("testuser", "password", "John", "Doe", "123 Main St", "1234567890", "john.doe@example.com");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User createdUser = userService.createUser(user);

        // Assert
        assertNotNull(createdUser);
        assertEquals(user.getUsername(), createdUser.getUsername());
    }

    @Test
    void createUser_ExistingUsername_ThrowsException() {
        // Arrange
        User existingUser = new User("existinguser", "password", "Jane", "Smith", "456 Elm St", "9876543210", "jane.smith@example.com");
        when(userRepository.findByUsername(existingUser.getUsername())).thenReturn(existingUser);

        // Act & Assert
        assertThrows(Exception.class, () -> userService.createUser(existingUser));
    }

    @Test
    void getUser_ExistingId_ReturnsUser() throws UserNotFoundException {
        // Arrange
        int userId = 4;
        User user = new User("MDunberry09", "SunnyFlower$", "Nuntapak", "Calabrese", "64 E Bay Street", "555-9922", "pchen01@email.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User retrievedUser = userService.getUser(userId);

        // Assert
        assertNotNull(retrievedUser);
        assertEquals(userId, retrievedUser.getId());
    }

    @Test
    void getUser_NonExistingId_ThrowsException() {
        // Arrange
        int nonExistingUserId = 999;
        when(userRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.getUser(nonExistingUserId));
    }

    @Test
    void updateUser_ExistingUser_ReturnsUpdatedUser() throws UserNotFoundException {
        // Arrange
        User existingUser = new User("testuser", "password", "John", "Doe", "123 Main St", "1234567890", "john.doe@example.com");
        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Modify user details
        existingUser.setFirstName("Updated");

        // Act
        User updatedUser = userService.updateUser(existingUser);

        // Assert
        assertNotNull(updatedUser);
        assertEquals("Updated", updatedUser.getFirstName());
    }

    @Test
    void updateUser_NonExistingUser_ThrowsException() {
        // Arrange
        User nonExistingUser = new User("nonexisting", "password", "Jane", "Smith", "456 Elm St", "9876543210", "jane.smith@example.com");
        when(userRepository.findById(nonExistingUser.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(nonExistingUser));
    }

    @Test
    void deleteUser_ExistingUser_DeletesUser() throws UserNotFoundException {
        // Arrange
        int userId = 1;
        User existingUser = new User("testuser", "password", "John", "Doe", "123 Main St", "1234567890", "john.doe@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteUser_NonExistingUser_ThrowsException() {
        // Arrange
        int nonExistingUserId = 999;
        when(userRepository.findById(nonExistingUserId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(nonExistingUserId));
        verify(userRepository, never()).deleteById(nonExistingUserId);
    }
}
