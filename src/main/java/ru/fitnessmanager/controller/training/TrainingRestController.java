package ru.fitnessmanager.controller.training;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.Training;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = TrainingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainingRestController extends AbstractTrainingController {

    static final String REST_URL = "/rest/trainings/{userId}/{exerciseId}";

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public List<Training> getAll(@PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        return super.getAll(userId, exerciseId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Training get(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
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
    public void update(@RequestBody Training training, @PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        super.update(training, id, userId, exerciseId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Training> createWithLocation(@RequestBody Training training, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        Training created = super.create(training, userId, exerciseId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
