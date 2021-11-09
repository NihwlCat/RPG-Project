package br.pedro.rproject.auth;

import br.pedro.rproject.models.entities.Player;
import br.pedro.rproject.services.PlayerService;
import br.pedro.rproject.services.ServiceException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class TokenAuthFilter extends UsernamePasswordAuthenticationFilter {

    public static final long TOKEN_EXP = 7200000L;
    public static final String SECRET = "SECRET";

    private final PlayerService service;

    private final AuthenticationManager authenticationManager;

    public TokenAuthFilter(PlayerService service, AuthenticationManager authenticationManager) {
        this.service = service;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        try {
            String cod = request.getParameter("username");
            Player p = service.recoverPlayer(cod);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    p.getId(),
                    "DEFAULT",
                    Collections.singletonList(new SimpleGrantedAuthority(p.getRole()))
            ));
        } catch (ServiceException ex){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Player p = service.recoverPlayer(user.getUsername());

        String token = JWT.create().
                withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXP))
                .withClaim("name",p.getName())
                .withClaim("role",p.getRole())
                .sign(Algorithm.HMAC512(SECRET));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
