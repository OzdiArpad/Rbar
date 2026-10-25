package com.example.rbar_bcknd.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class Login {
    @GetMapping("/Login")
    public void  BtnLogIn(@AuthenticationPrincipal OAuth2User user, HttpServletResponse response) throws IOException{
        response.sendRedirect("http://localhost:4200/Login");

    }
}
