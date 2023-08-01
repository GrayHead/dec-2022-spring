package ua.com.owu.dec2022spring.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ua.com.owu.dec2022spring.dao.AppUserDAO;
import ua.com.owu.dec2022spring.models.ResponseError;
import ua.com.owu.dec2022spring.services.JwtService;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    private AppUserDAO appUserDAO;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


        String authorizationHeader = request.getHeader("Authorization"); // Bearer tokenEMAILtoken
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String jwt = authorizationHeader.substring(7);
            String userEmail = jwtService.extractUsername(jwt);

            if (userEmail != null && SecurityContextHolder
                    .getContext()
                    .getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)
                        &&
                        !jwt.equals(appUserDAO
                                .findAppUserByEmail(userEmail)
                                .getRefreshToken())
                ) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authenticationToken);

                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (UsernameNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ExpiredJwtException e) {
            response.setHeader("TokenError", "token dead");
            response.resetBuffer();
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            ResponseError responseError = ResponseError
                    .builder()
                    .error("token is dead")
                    .code(403)
                    .build();

            response
                    .getOutputStream()
                    .write(new ObjectMapper().writeValueAsBytes(responseError));

            return;


        }


        //--------======================================
        filterChain.doFilter(request, response);
    } // doFilterInternal
}
