package com.spu.TourismApp.Security.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// @Component annotation registers the class as a Spring bean, enabling it for dependency injection.
// The filter will be automatically added to the Spring Security filter chain.
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // JwtService is a custom service responsible for handling JWT-related operations (like extracting username, validating the token).
    private final JwtService jwtService;

    // UserDetailsService is a Spring interface used to retrieve user-related data from a persistent store (like a database).
    private final UserDetailsService userDetailsService;

    // The doFilterInternal method is overridden to implement the logic for processing each incoming request.
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // The HTTP request to be processed.
            @NonNull HttpServletResponse response, // The HTTP response that will be returned.
            @NonNull FilterChain filterChain // The chain of filters in the Spring Security filter chain.
    ) throws ServletException, IOException {
        // Extract the Authorization header from the incoming request.
        final String authHeader = request.getHeader("Authorization");
        final String jwt; // Placeholder for the JWT token extracted from the header.
        final String userEmail; // Placeholder for the username (email) extracted from the JWT token.

        // If the Authorization header is missing or doesn't start with "Bearer ", just continue the filter chain.
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response); // Proceed with the next filter in the chain.
            return;
        }

        // Extract the JWT token from the Authorization header by removing the "Bearer " prefix.
        jwt = authHeader.substring(7);

        // Extract the username (email) from the JWT token.
        userEmail = jwtService.extractUsername(jwt);

        // If the username is found and there's no active authentication in the SecurityContext (no user is logged in),
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Load user details from the UserDetailsService using the username (email).
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Validate the JWT token. If it's valid, create an authentication token.
            if(jwtService.isTokenValid(jwt, userDetails)){
                // Create an Authentication token (UsernamePasswordAuthenticationToken), which represents the authenticated user.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // No credentials are provided in this case, since we only validate the token.
                        userDetails.getAuthorities() // The user's authorities (roles and permissions).
                );

                // Set the details for the authentication token (information about the current request).
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the authentication token in the SecurityContext, marking the user as authenticated.
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Continue the filter chain after processing the request (authentication logic).
        filterChain.doFilter(request, response);
    }
}
