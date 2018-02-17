package ru.fitnessmanager.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import ru.fitnessmanager.ActiveUser;
import ru.fitnessmanager.controller.user.AbstractUserController;
import ru.fitnessmanager.model.Role;
import ru.fitnessmanager.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class RootController extends AbstractUserController {

    @GetMapping(value = "/")
    public String root() {
        return "redirect:main";
    }

    @GetMapping(value = "/index")
    public String login() {
        return "index";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(Model model) {
        if (ActiveUser.get().getUser().getRoles().contains(Role.ROLE_ADMIN)) {
            return "redirect:admin";
        } else {
            return "main";
        }
    }

    @RequestMapping(value = "/parameters", method = RequestMethod.GET)
    public String parameters(Model model) {
        if (ActiveUser.get().getUser().getRoles().contains(Role.ROLE_ADMIN)) {
            return "redirect:admin";
        } else {
            return "parameters";
        }
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        if (ActiveUser.get().getUser().getRoles().contains(Role.ROLE_ADMIN)) {
            return "admin";
        } else {
            return "redirect:main";
        }
    }


    @PostMapping("/register")
    public String saveRegister(@Valid User user, BindingResult result, SessionStatus status, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("register", true);
            return "register";
        } else {
            super.create(user);
            status.setComplete();
            return "redirect:index";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}
