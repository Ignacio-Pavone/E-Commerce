package com.ecommerce.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface IJwtService {
    String extractUserName(String token);
    Date extractExpiration(String token);
    Date extractClaimDate(String token);
    String extractClaimUsername(String token);
    String generateToken(UserDetails userDetails);
    Boolean validateToken(String token, UserDetails userDetails);
}
