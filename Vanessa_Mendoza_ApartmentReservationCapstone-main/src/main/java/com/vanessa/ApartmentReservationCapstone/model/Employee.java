package com.vanessa.ApartmentReservationCapstone.model;

import jakarta.persistence.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 7, max = 15, message = "Username must be between 7 and 15 characters")
    private String username;
    @Column(nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Column(nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String fullName;
    @Column(nullable = false)
    @NotBlank(message = "Role cannot be blank")
    private String role;
    @Column(nullable = false)
    @NotBlank(message = "Address cannot be blank")
    private String homeAddress;
    @Column(nullable = false)
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    @Column(nullable = false)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String emailAddress;
    public Employee() {
    }

    public Employee(String username, String password, String fullName,
                    String role, String homeAddress, String phoneNumber, String emailAddress) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String firstName) {
        this.fullName = firstName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
