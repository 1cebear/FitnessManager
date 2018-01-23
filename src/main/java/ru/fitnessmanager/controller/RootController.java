package ru.fitnessmanager.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fitnessmanager.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RootController {

    private static List<User> persons = new ArrayList<User>();

    static {
        persons.add(new User(3, "test", "test", "123", true, new Date(), null));
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {

        String message = "Hello Spring Boot + JSP";

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = {"/personList"}, method = RequestMethod.GET)
    public String viewPersonList(Model model) {

        model.addAttribute("persons", persons);

        return "personList";
    }
}
