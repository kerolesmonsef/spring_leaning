package com.example.qgame.configs.auth;

import com.example.qgame.Models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_ID = "id";
    private static final String CLAIM_KEY_ROLE = "role";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expires}")
    private Long expiration;


    public Long getUserIdFromToken(String token) {
        Long id = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            id = Long.valueOf((Integer) claims.get(CLAIM_KEY_ID));
        } catch (final Exception e) {
            System.err.println(e.getMessage());
        }
        return id;
    }


    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (final Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * Returns creation date from given token
     *
     * @param token JSON Web Token
     * @return creation date
     */
    public Date getCreationDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (final Exception e) {
            created = null;
        }
        return created;
    }

    /**
     * Returns expiration date from given token
     *
     * @param token JSON Web Token
     * @return expiration date
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (final Exception e) {
            expiration = null;
        }
        return expiration;
    }

    /**
     * Generates JWT using userDetails
     *
     * @param userDetails used to generate JWT
     * @return generated JWT
     */
    public String generateToken(UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        final User jwtUser = (User) userDetails;
        claims.put(CLAIM_KEY_ID, jwtUser.getId());
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_ROLE, userDetails.getAuthorities());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (final Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }


    public Boolean isTokenValid(String token) {
        final String username = getUsernameFromToken(token);
        if (username == null) {
            return false;
        } else {
            return !isTokenExpired(token);
        }
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (final Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
