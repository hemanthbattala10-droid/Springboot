package com.example.okta_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal OidcUser user) {
        if (user != null) {
            model.addAttribute("name", user.getFullName());
            model.addAttribute("email", user.getEmail());
        }
        return "home"; // maps to home.html
    }

    @GetMapping("/secure")
    public String secure(Model model, @AuthenticationPrincipal OidcUser user) {
        if (user == null) {
            return "redirect:/"; // redirect to home if not authenticated
        }

        // 🔍 Log user details for debugging
        System.out.println("User object: " + user);
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("ID Token: " + user.getIdToken().getTokenValue());

        // ✅ Add attributes to the model
        model.addAttribute("name", user.getFullName());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("token", user.getIdToken().getTokenValue());

        return "secure"; // maps to secure.html
    }
}
