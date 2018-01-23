package ru.fitnessmanager.controller.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fitnessmanager.model.User;

import java.util.List;

@RestController
@RequestMapping(value = UserRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractUserController {

    static final String REST_URL = "/rest/users";

    @Override
    @GetMapping
    public List<User> getAll() {
        return super.getAll();
    }

    public User get(int id) {
        return super.get(id);
    }
}
