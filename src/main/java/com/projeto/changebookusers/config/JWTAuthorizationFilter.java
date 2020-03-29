package com.projeto.changebookusers.config;

import com.projeto.changebookusers.service.ChangeBookDetailsService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final ChangeBookDetailsService changeBookDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, ChangeBookDetailsService changeBookDetailsService) {
        super(authenticationManager);
        this.changeBookDetailsService = changeBookDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization_header = request.getHeader(SecurityConstraints.HEADER_STRING);
        if (authorization_header == null || authorization_header.startsWith(SecurityConstraints.TOKEN_PREFIX)){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            chain.doFilter(request, response);
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader(SecurityConstraints.HEADER_STRING);
        if (token == null) return null;
        String email = Jwts.parser()
                .setSigningKey(SecurityConstraints.SECRET)
                .parseClaimsJws(token.replace(SecurityConstraints.TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        UserDetails userDetails = changeBookDetailsService.loadUserByUsername(email);

        return email != null ? new UsernamePasswordAuthenticationToken(email, null, userDetails.getAuthorities()) : null;

    }
}
