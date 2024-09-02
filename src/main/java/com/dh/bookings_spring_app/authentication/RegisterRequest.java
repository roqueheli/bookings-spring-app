package com.dh.bookings_spring_app.authentication;

import java.util.Set;

import com.dh.bookings_spring_app.entities.Addresses;
import com.dh.bookings_spring_app.entities.UserPlaces;
import com.dh.bookings_spring_app.entities.UserType;
import com.dh.bookings_spring_app.entities.Users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String document;
    private String email;
    private String password;
    private String phone;
    private UserType userType;
    private Addresses address;
    private Set<UserPlaces> userPlaces;
    private Users owner;
}
