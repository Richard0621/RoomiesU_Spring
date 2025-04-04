package com.roomies.roomiesu.security.filters;

import com.roomies.roomiesu.security.jwt.JwtUtils;
import com.roomies.roomiesu.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//Este filtro se encarga de validar el token JWT en cada peticion que llega al servidor

@Component
public class JwtAutorizationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        //Extrarer el token de la cabecera de la peticion
        String tokenHeader = request.getHeader("Authorization");

        //Validar que el token no sea nulo
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(7);
            //Validar el token
            if (jwtUtils.isTokenValid(token)) {
                //Extraer el username del token
                String username = jwtUtils.getUsernameFromToken(token);
                //Cargar el usuario por su username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //Crear el authentication
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                //Establecer el authentication en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response); //Continuar con el filtro
    }
}
