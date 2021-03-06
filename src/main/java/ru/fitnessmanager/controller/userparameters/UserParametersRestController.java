package ru.fitnessmanager.controller.userparameters;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.fitnessmanager.model.UserParameters;
import ru.fitnessmanager.to.UserParametersTo;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = UserParametersRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserParametersRestController extends AbstractUserParametersController {

    static final String REST_URL = "/rest/userparameters/{userId}";

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{parameterId}")
    public List<UserParameters> getAll(@PathVariable("userId") int userId, @PathVariable("parameterId") int parameterId) {
        return super.getAll(userId, parameterId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{parameterId}/{id}")
    public UserParameters get(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("parameterId") int parameterId) {
        return super.get(id, userId, parameterId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{parameterId}/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("parameterId") int parameterId) {
        super.delete(id, userId, parameterId);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{parameterId}/{id}")
    public void update(@RequestBody UserParameters userParameters, @PathVariable("id") int id, @PathVariable("userId") int userId, @PathVariable("parameterId") int parameterId) {
        super.update(userParameters, id, userId, parameterId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/{parameterId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserParameters> createWithLocation(@RequestBody UserParameters userParameters, @PathVariable("userId") int userId, @PathVariable("parameterId") int parameterId) {
        UserParameters created = super.create(userParameters, userId, parameterId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(userId, parameterId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/foruser")
    public List<UserParametersTo> getForUser(@RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, @PathVariable("userId") int userId) {
        return super.getForUser(startDate, endDate, userId);
    }
}
