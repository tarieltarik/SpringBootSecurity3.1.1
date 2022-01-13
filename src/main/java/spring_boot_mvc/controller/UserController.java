package spring_boot_mvc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_boot_mvc.model.User;
import spring_boot_mvc.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal) {
        User user = userService.getUserByName(principal.getName()).get();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        return "hello";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
