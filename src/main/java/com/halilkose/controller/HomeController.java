package com.halilkose.controller;

import com.halilkose.model.User;
import com.halilkose.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, Model model) {
        if (userService.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Bu e-posta zaten kayıtlı!");
            return "register";
        }

        userService.saveUser(user);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userService.findByEmail(username);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Geçersiz kullanıcı adı veya şifre");
            return "login";
        }
        session.setAttribute("loggedInUser", user);

        return "redirect:/user/index";
    }

    @GetMapping("/user/index")
    public String userDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "user/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}


