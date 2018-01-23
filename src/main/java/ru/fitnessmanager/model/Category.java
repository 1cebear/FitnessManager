package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    @JsonManagedReference(value = "a_name")
    private Set<Exercise> exerciseSet;

    public Set<Exercise> getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(Set<Exercise> exerciseSet) {
        this.exerciseSet = exerciseSet;
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

    public Category(Integer id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
