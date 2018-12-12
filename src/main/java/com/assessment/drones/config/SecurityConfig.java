package com.assessment.drones.config;

import com.assessment.drones.handlers.AuthFailureHandler;
import com.assessment.drones.handlers.AuthSuccessHandler;
import com.assessment.drones.services.implementations.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AuthSuccessHandler authSuccessHandler;
    private AuthFailureHandler authFailureHandler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthSuccessHandler authSuccessHandler, AuthFailureHandler authFailureHandler) {
        this.userDetailsService = userDetailsService;
        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
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
                .antMatchers("/register", "/registrationConfirm", "/forgottenPassword", "/passwordReset", "/").permitAll()
                .antMatchers("/updatePassword").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .antMatchers("/login").permitAll()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/dashboard").hasRole("candidate")
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
