package com.assessment.asg.handlers;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
                                        final AuthenticationException exception) throws IOException, ServletException {

        setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);

        String errorMessage = "";

        //Useful for adding new exceptions later
//        System.out.println("Exception is: " + exception);

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = "Sorry, but that account has not been activated yet. Please look for an email "
                    + "with an activation link. After you've used it, you can log in.";
        } else if (exception.getMessage().equalsIgnoreCase("Bad credentials")) {
            errorMessage = "Sorry, but your credentials do not match any that we have on record";
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}
