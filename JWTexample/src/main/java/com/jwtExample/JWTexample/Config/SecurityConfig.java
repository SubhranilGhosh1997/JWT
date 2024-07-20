package com.jwtExample.JWTexample.Config;

import com.jwtExample.JWTexample.Model.Permission;
import com.jwtExample.JWTexample.Model.Role;
import com.jwtExample.JWTexample.security.JwtAuthenticationEntryPoint;
import com.jwtExample.JWTexample.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //Configuration
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth-> auth
                        .requestMatchers(HttpMethod.POST,"/users/**").permitAll()
                        .requestMatchers("/users/security-util").permitAll()
                        .requestMatchers("/admins/**").hasRole(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET,"/admins/**").hasAuthority(Permission.ADMIN_READ.name())
                        .requestMatchers(HttpMethod.POST,"/admins/**").hasAuthority(Permission.ADMIN_CREATE.name())
                        .requestMatchers(HttpMethod.PUT,"/admins/**").hasAuthority(Permission.ADMIN_UPDATE.name())
                        .requestMatchers(HttpMethod.DELETE,"/admins/**").hasAuthority(Permission.ADMIN_DELETE.name())
//                        .requestMatchers("/management/**").hasAnyRole(Role.ADMIN.name(),Role.MANAGER.name())
//                        .requestMatchers(HttpMethod.GET,"/management/**").hasAnyAuthority(Permission.ADMIN_READ.name(),Permission.MANAGER_READ.name())
//                        .requestMatchers(HttpMethod.POST,"/management/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(),Permission.MANAGER_CREATE.name())
//                        .requestMatchers(HttpMethod.PUT,"/management/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(),Permission.MANAGER_UPDATE.name())
//                        .requestMatchers(HttpMethod.DELETE,"/management/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(),Permission.MANAGER_DELETE.name())
                        .requestMatchers("/users/**").authenticated()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return  daoAuthenticationProvider;
    }
}
