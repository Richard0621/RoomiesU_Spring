
package com.roomies.roomiesu.security.jwt;
// Esta clase nos proporciona los metodos para generar y validar el token JWT
// y para obtener la información del usuario a partir del token

import com.roomies.roomiesu.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private String timeExpiration;

    // GENERAR TOKEN DE ACCESO
    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setSubject(email) // el subject es el email Que se loguea
                .setIssuedAt(new Date(System.currentTimeMillis()))  // fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration))) // fecha de expiración
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256) // firma del token
                .compact(); // compact() genera el token
    }

    // VALIDAR TOKEN DE ACCESO
    public boolean isTokenValid(String token) {
        try { // parseamos el token, o sea lo leemos y validamos la firma
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true; // si no lanza excepción, el token es válido
        } catch (Exception e) {
            log.error("Error al validar el token: {}", e.getMessage()); // si lanza excepción, el token no es válido
            return false; // si lanza excepción, el token no es válido
        }
    }

    // OBTENER EL NOMBRE DE USUARIO DEL TOKEN
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    // OBTENER UN SOLO CLAIM DEL TOKEN
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token); // parseamos el token
        return claimsTFunction.apply(claims);// obtenemos el claim que queremos
    }


    // OBTENER TODOS LOS CLAIMS DEL TOKEN, o sea, la información del user que viene en el token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody(); // obtenemos el body del token, que es donde está la información del usuario
    }

    // OBTENER FIRMA DEL TOKEN
    public Key getSignatureKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey); // decodificamos la clave secreta
        return Keys.hmacShaKeyFor(keyBytes); // generamos la firma
    }
}

