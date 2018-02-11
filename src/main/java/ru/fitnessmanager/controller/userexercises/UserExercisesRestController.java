package ru.fitnessmanager.controller.userexercises;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.UserExercises;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserExercisesRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserExercisesRestController extends AbstractUserExercisesController {

    static final String REST_URL = "/rest/userexercises/{userId}/{exerciseId}";

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public List<UserExercises> getAll(@PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        return super.getAll(userId, exerciseId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public UserExercises get(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        return super.get(id, userId, exerciseId);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        super.delete(id, userId, exerciseId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public void update(@RequestBody UserExercises userExercises, @PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        super.update(userExercises, id, userId, exerciseId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserExercises> createWithLocation(@RequestBody UserExercises userExercises, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        UserExercises created = super.create(userExercises, userId, exerciseId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(userId, exerciseId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
