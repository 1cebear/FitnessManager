package ru.fitnessmanager.controller.exercise;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.Exercise;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ExerciseRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ExerciseRestController extends AbstractExerciseController {

    static final String REST_URL = "/rest/categories/{categoryId}/exercises";

    @Override
    @GetMapping
    public List<Exercise> getAll(@PathVariable("categoryId") int categoryId) {
        return super.getAll(categoryId);
    }

    @Override
    @GetMapping("/{id}")
    public Exercise get(@PathVariable("id") int id, @PathVariable("categoryId") int categoryId) {
        return super.get(id, categoryId);
    }


    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("categoryId") int categoryId) {
        super.delete(id, categoryId);
    }

    @Override
    @PutMapping("/{id}")
    public void update(@RequestBody Exercise exercise, @PathVariable("id") int id, @PathVariable("categoryId") int categoryId) {
        super.update(exercise, id, categoryId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exercise> createWithLocation(@RequestBody Exercise exercise, @PathVariable("categoryId") int categoryId) {
        Exercise created = super.create(exercise, categoryId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
