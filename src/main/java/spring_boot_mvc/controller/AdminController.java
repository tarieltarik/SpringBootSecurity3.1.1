package spring_boot_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring_boot_mvc.model.Role;
import spring_boot_mvc.model.User;
import spring_boot_mvc.service.RoleService;
import spring_boot_mvc.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = new ArrayList<>();
        userService.allUsers().forEach(users::add);
        model.addAttribute("authorizedUser", userDetails);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", users);
        model.addAttribute("allRoles", roleService.allRoles());
        return "admin";
    }


    @PostMapping(value = "/create")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("authorities") String[] roles) {
        Set<Role> Setroles = new HashSet<>();
        for (String st : roles) {
            if (st.equals("ADMIN")) {
                Role role_admin = roleService.getRoleById(2L);
                Setroles.add(role_admin);
            }
            if (st.equals("USER")) {
                Role role_user = roleService.getRoleById(1L);
                Setroles.add(role_user);
            }
        }
        user.setRoles(Setroles);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/update")
    public String editUser(@ModelAttribute("user") User user,
                           @RequestParam("authorities") String[] roles){
        Set<Role> setroles = new HashSet<>();
        for (String st : roles) {
            if (st.equals("ADMIN")) {
                Role role_admin = roleService.getRoleById(2L);
                setroles.add(role_admin);
            }
            if (st.equals("USER")) {
                Role role_user = roleService.getRoleById(1L);
                setroles.add(role_user);
            }
        }
        user.setRoles(setroles);
        userService.save(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }
}

