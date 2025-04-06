package com.roomies.roomiesu.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roomies.roomiesu.model.Usuario;
import com.roomies.roomiesu.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Usuario usuario = null;
        String email = "";  // Cambiado de username a email
        String password = "";

        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            email = usuario.getEmail();  // Cambiado de getUsername() a getEmail()
            password = usuario.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // El token ahora usa email en lugar de username
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        return getAuthenticationManager().authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        String token = jwtUtils.generateAccessToken(user.getUsername()); // Aquí sigue usando getUsername() porque Spring Security internamente usa username

        // 👇🏻 AGREGA ESTOS HEADERS PARA PERMITIR CORS
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        // 👇🏻 TAMBIÉN ESTABLECE TIPO Y STATUS ANTES DE USAR getWriter
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // 👇🏻 CONSTRUIMOS Y ENVIAMOS RESPUESTA JSON
        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token);
        httpResponse.put("Message", "Autenticación Correcta");
        httpResponse.put("email", user.getUsername());

        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
        response.getWriter().flush();
//        response.addHeader("Authorization", token);
//
//        Map<String, Object> httpResponse = new HashMap<>();
//        httpResponse.put("token", token);
//        httpResponse.put("Message", "Autenticación Correcta");
//        httpResponse.put("email", user.getUsername()); // Cambiado de username a email en la respuesta
//
//        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse));
//        response.setStatus(HttpStatus.OK.value());
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getWriter().flush();
//
//        super.successfulAuthentication(request, response, chain, authResult);
    }
}





















/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roomies.roomiesu.model.Usuario;
import com.roomies.roomiesu.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// Nos permite autenticar al usuario con su nombre de usuario y contraseña
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtUtils jwtUtils;


    public JwtAuthenticationFilter(JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.jwtUtils = jwtUtils;
        setAuthenticationManager(authenticationManager); // <-- Configura el AuthenticationManager
    }
    // Este metodo se encarga de autenticar al usuario
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        Usuario usuario = null;
        String username = "";
        String password = "";

        try {
            usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
            username = usuario.getUsername();
            password = usuario.getPassword();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password); // Permite autenticar al usuario con su nombre de usuario y contraseña


        return getAuthenticationManager().authenticate(authenticationToken); // el metodo salio de la clase padre de UsernamePasswor...

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal(); // obtenemos el usuario autenticado ese User es el que viene de la clase UserDetailsService
        String token = jwtUtils.generateAccessToken(user.getUsername()); // generamos el token JWT

        response.addHeader("Authorization", token); // agregamos el token a la respuesta

        Map<String, Object> httpResponse = new HashMap<>();
        httpResponse.put("token", token); // agregamos el token a la respuesta
        httpResponse.put("Message", "Autenticación Correcta"); // agregamos el usuario a la respuesta
        httpResponse.put("username", user.getUsername()); // agregamos el usuario a la respuesta


        response.getWriter().write(new ObjectMapper().writeValueAsString(httpResponse)); // convertimos el objeto a JSON y lo escribimos en la respuesta
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // establecemos el tipo de contenido de la respuesta a JSON
        response.getWriter().flush(); // nos aseguramos de que se escriba la respuesta ya que limpia el buffer

        super.successfulAuthentication(request, response, chain, authResult);
    }
}


*/