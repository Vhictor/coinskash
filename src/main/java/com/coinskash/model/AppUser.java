package com.coinskash.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "firstName")
    @NotBlank(message = "First Name is required*")
    private String firstName;
    @Column(name = "lastName")
    @NotBlank(message = "First Name is required*")
    private String lastName;
    @Column(name = "email")
    @NotBlank(message = "Email is required*")
    @Email(message = "Please enter a valid email address*")
    private String username;
    @Column(name = "country")
    @NotBlank(message = "Country field is required*")
    private String country;
    @Column(name = "phoneNo")
    @NotBlank(message = "Mobile number is required*")
    private String phoneNo;
    @NotBlank(message = "Password is required*")
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Collection<Roles> roles = new ArrayList<>();

}
