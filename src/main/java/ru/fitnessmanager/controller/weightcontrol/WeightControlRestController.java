package ru.fitnessmanager.controller.weightcontrol;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.WeightControl;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = WeightControlRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class WeightControlRestController extends AbstractWeightControlController{

    static final String REST_URL = "/rest/users/{userId}/weight";

    @Override
    @GetMapping
    public List<WeightControl> getAll(@PathVariable("userId") int userId) {
        return super.getAll(userId);
    }

    @Override
    @GetMapping("/{id}")
    public WeightControl get(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        return super.get(id, userId);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        super.delete(id, userId);
    }

    @Override
    @PutMapping("/{id}")
    public void update(@RequestBody WeightControl weightControl, @PathVariable("id") int id, @PathVariable("userId") int userId) {
        super.update(weightControl, id, userId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WeightControl> createWithLocation(@RequestBody WeightControl weightControl, @PathVariable("userId") int userId) {
        WeightControl created = super.create(weightControl, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
