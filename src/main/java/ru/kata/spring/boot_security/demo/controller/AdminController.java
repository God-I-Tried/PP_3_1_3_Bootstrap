package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;
import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService, RoleRepository roleRepository, RoleRepository roleRepository1) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAdminPage(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("allRoles", roleService.findAll());
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("admin", userService.showUser(user.getId()));
        model.addAttribute("addUser", new User());
        return "admin";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("addUser") User user, @RequestParam(name = "selectedRoles", required = false) Long[] selectedRoles, @PathVariable("id") Long id) {
        userService.editUser(id, user, selectedRoles);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deletedUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam(name = "selectedRoles", required = false) Long[] selectedRoles) {
        userService.addUser(user, Arrays.asList(selectedRoles));
        return "redirect:/admin";
    }

}
