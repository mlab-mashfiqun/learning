package com.mashfiqun.myfancypdfinvoices.web;

import com.mashfiqun.myfancypdfinvoices.web.forms.LoginForm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
public class WebsiteController {
    @GetMapping("/")
    public String homepage(Model model, @RequestParam(name = "username", required = false, defaultValue = "stranger") String username) {
        model.addAttribute("username", username);
        model.addAttribute("currentDate", LocalTime.now());
        return "index.html";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login.html";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "login.html";
        }
        if (loginForm.getUsername().equals(loginForm.getPassword())) {
            return "redirect:/";
        }
        else {
            model.addAttribute("invalidCredentials", "true");
            return "login.html";
        }
    }
}
