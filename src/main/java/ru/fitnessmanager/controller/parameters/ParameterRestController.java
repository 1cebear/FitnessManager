package ru.fitnessmanager.controller.parameters;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.Parameter;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = ParameterRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ParameterRestController extends AbstractParameterController {

    static final String REST_URL = "/rest/parameters";

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public List<Parameter> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public Parameter get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Parameter parameter, @PathVariable("id") int id) {
        super.update(parameter, id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Parameter> createWithLocation(@RequestBody Parameter parameter) {
        Parameter created = super.create(parameter);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
