package com.chaersi.mode_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        // If no token -> continue call, public APIs are allowed
        if(authHeader == null || authHeader.startsWith("Bearer ") != true) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            boolean isTokenValid = jwtService.validateToken(token);
            if(isTokenValid) {
                // Approving Authentication of Security
                String deviceId = jwtService.extractDeviceId(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        deviceId,
                        null,
                        List.of()
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
                // Token is Valid, continue request
                filterChain.doFilter(request, response);
            } else {
                // Invalid Token
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid Authentication Token");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authentication Validation Failed" + "| Error Message: " + e.getMessage());
        }
    }
}
