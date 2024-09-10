package com.dh.bookings_spring_app.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRefreshResponse {
    private String accessToken;
    private String refreshToken;
}
