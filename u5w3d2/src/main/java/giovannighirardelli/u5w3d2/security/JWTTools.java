package giovannighirardelli.u5w3d2.security;


import giovannighirardelli.u5w3d2.entities.Dipendenti;
import giovannighirardelli.u5w3d2.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    public  String createToken(Dipendenti dipendenti){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 *24 *7))
                .subject(String.valueOf(dipendenti.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    public void verifyToken(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex){
            throw new UnauthorizedException("Problemi con il token! Effettua di nuovo il login");
        }
    }

    public String extractIdFromToken(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
