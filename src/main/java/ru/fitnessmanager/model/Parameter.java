package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parameters")
public class Parameter extends BaseEntity{

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parameter")
    @JsonManagedReference(value = "e_name")
    private Set<UserParameters> userParametersSet;

    public Set<UserParameters> getExerciseSet() {
        return userParametersSet;
    }

    public void setExerciseSet(Set<UserParameters> userParametersSet) {
        this.userParametersSet = userParametersSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Parameter() {
    }

    public Parameter(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
