package com.assessment.asg.config;

import com.assessment.asg.handlers.AuthFailureHandler;
import com.assessment.asg.handlers.AuthSuccessHandler;
import com.assessment.asg.services.UserDetailsServiceImpl;
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
    public SecurityConfig(final UserDetailsServiceImpl userDetailsService, final AuthSuccessHandler authSuccessHandler,
                          final AuthFailureHandler authFailureHandler) {
        this.userDetailsService = userDetailsService;
        this.authSuccessHandler = authSuccessHandler;
        this.authFailureHandler = authFailureHandler;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/register", "/login", "/forgottenPassword", "/passwordReset").permitAll()
                .antMatchers("/css/**", "/js/**", "/vendor/**").permitAll()
                .antMatchers("/updatePassword").hasAuthority("CHANGE_PASSWORD_PRIVILEGE")
                .antMatchers("/admin", "/chart").hasRole("admin")
                .antMatchers("/dashboard", "/downloadOpsManual").hasRole("candidate")
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
