package com.dh.bookings_spring_app.authentication;

import com.dh.bookings_spring_app.configuration.JwtService;
import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.repository.UsersRepository;
import com.dh.bookings_spring_app.service.CustomUserDetailsService;
import com.dh.bookings_spring_app.service.IUsersService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IUsersService iUsersService;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Users.builder()
                .name(request.getName())
                .document(request.getDocument())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .userType(request.getUserType())
                .address(request.getAddress())
                .places(request.getPlaces())
                .owner(request.getOwner())
                .build();

        var userSaved = iUsersService.save(user);

        return AuthenticationResponse.builder().token(jwtService.generateToken(userSaved)).build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = usersRepository.findByEmail(request.getEmail()).orElseThrow();

        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }

    public AuthenticationResponse admin(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = usersRepository.findByAdmin(request.getEmail()).orElseThrow();
        System.out.println(user);

        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }

    public AuthenticationResponse refresh(String refreshToken) {
        String userEmail = jwtService.extractUsername(refreshToken);

        // Usar la instancia inyectada para cargar el usuario
        UserDetails userDetails;
        try {
            userDetails = customUserDetailsService.loadUserByUsername(userEmail);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException("Usuario no encontrado", e);
        }

        if (jwtService.isTokenValid(refreshToken, userDetails)) {
            Users user = usersRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            String newAccessToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(newAccessToken)
                    .name(user.getName())
                    .id(user.getUser_id().toString())
                    .build();
        } else {
            throw new RuntimeException("Refresh token inválido o expirado");
        }
    }
}
