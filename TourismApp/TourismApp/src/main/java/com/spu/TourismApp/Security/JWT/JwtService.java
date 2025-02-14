package com.spu.TourismApp.Security.JWT;

import com.spu.TourismApp.Security.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.spu.TourismApp.Security.SecurityUtils.getSignInKey;

@Service
public class JwtService {

    // The SECRET_KEY is a static constant that is assigned a value during the static block initialization.
    private static final String SECRET_KEY;

    static {
        try {
            SECRET_KEY = SecurityUtils.secretKey(); // SecurityUtils.secretKey() generates a secret key for signing the JWT.
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // If the secret key can't be generated, the exception is thrown.
        }
    }

    // Method to extract the username (subject) from the JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Generic method to extract a specific claim from the JWT token, such as the username or expiration date.
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token); // Extract all claims from the token
        return claimsResolver.apply(claims); // Return the specific claim based on the provided resolver function.
    }

    // Method to extract all claims from the JWT token using the signing key.
    private Claims extractAllClaims(String token){
        return Jwts
                .parser() // JWT parser to parse the token
                .setSigningKey(getSignInKey(SECRET_KEY)) // Set the signing key for validation
                .parseClaimsJws(token) // Parse and validate the JWT token
                .getBody(); // Extract the claims from the token body
    }

    // Method to generate a JWT token for a user based on user details.
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails); // Delegate to the other generateToken method with empty extra claims.
    }

    // Overloaded method to generate a JWT token with additional claims
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extraClaims) // Add any extra claims
                .setSubject(userDetails.getUsername()) // Set the username as the subject of the token
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issue time
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Set the expiration time (24 hours from now)
                .signWith(SignatureAlgorithm.HS256, getSignInKey(SECRET_KEY)) // Sign the token using the secret key and HS256 algorithm
                .compact(); // Build the token
    }

    // Method to validate a token by checking if it matches the username and is not expired
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token); // Extract the username from the token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Validate the token's username and expiration
    }

    // Method to check if the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); // Compare the token's expiration date with the current date
    }

    // Method to extract the expiration date from the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration); // Use the extractClaim method to retrieve the expiration claim
    }
}
