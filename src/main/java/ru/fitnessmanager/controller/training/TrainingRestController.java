package ru.fitnessmanager.controller.training;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.Training;
import ru.fitnessmanager.to.TrainingTo;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = TrainingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TrainingRestController extends AbstractTrainingController {

    static final String REST_URL = "/rest/trainings";

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{userId}/{exerciseId}")
    public List<Training> getAll(@PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        return super.getAll(userId, exerciseId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{userId}/{exerciseId}/{id}")
    public Training get(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        return super.get(id, userId, exerciseId);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{userId}/{exerciseId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        super.delete(id, userId, exerciseId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{userId}/{exerciseId}/{id}")
    public void update(@RequestBody Training training, @PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        super.update(training, id, userId, exerciseId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/{userId}/{exerciseId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Training> createWithLocation(@RequestBody Training training, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        Training created = super.create(training, userId, exerciseId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(userId, exerciseId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{userId}/{exerciseId}/filter")
    public List<Training> getBetween(@RequestParam(value = "startDate", required = false) Date startDate, @RequestParam(value = "endDate", required = false) Date endDate, @PathVariable("userId") int userId, @PathVariable("exerciseId") int exerciseId) {
        return super.getBetween(startDate, endDate, userId, exerciseId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/foruser/{userId}")
    public List<TrainingTo> getTrainingsForUser(@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, @PathVariable("userId") int userId) {
        return super.getTrainingsForUser(startDate, endDate, userId);
    }
}
