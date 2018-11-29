package com.assessment.drones.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService userDetailsService;
    private AuthSuccessHandler authSuccessHandler;
    private AuthFailureHandler authFailureHandler;

    @Autowired
    public SecurityConfig(MyUserDetailsService aService, AuthSuccessHandler aAuthSuccessHandler, AuthFailureHandler aAuthFailureHandler) {
        userDetailsService = aService;
        authSuccessHandler = aAuthSuccessHandler;
        authFailureHandler = aAuthFailureHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register").permitAll()
                .antMatchers("/login", "/login-error").permitAll()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/dashboard").hasRole("client")
                .antMatchers("/css/**", "/js/**", "/vendor/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler);

        http.logout()
                .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
