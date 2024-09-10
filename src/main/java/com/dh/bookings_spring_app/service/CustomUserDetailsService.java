package com.dh.bookings_spring_app.service;

import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        return Users.builder()
                .user_id(user.getUser_id())
                .email(user.getEmail())
                .password(user.getPassword())
                .userType(user.getUserType()) // Si estás usando roles, ajústalo según tu implementación
                .build();
    }
}
