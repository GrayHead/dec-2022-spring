package ua.com.owu.dec2022spring.security;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ua.com.owu.dec2022spring.dao.CustomerDAO;
import ua.com.owu.dec2022spring.models.Customer;
import ua.com.owu.dec2022spring.services.CustomerService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private CustomerService customerService;



    // user in memmory -> db


    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("userDetailsService()");

        return username -> {
            System.out.println(username);
            Customer customer = customerService.findByUsername(username);
            User user = new User(
                    customer.getUsername(),
                    customer.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority(customer.getRole()))
            );
            return user;

        };
    }

    // filter chain url

    @SneakyThrows
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) {

        httpSecurity.authorizeRequests(auth ->
                                auth
                                        .requestMatchers(HttpMethod.GET, "/admin").hasAnyRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/user").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers(HttpMethod.POST,"/save").permitAll()
                                        .anyRequest().permitAll()


                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrfConfigurer -> csrfConfigurer.disable());

        return httpSecurity.build();
    }
}
