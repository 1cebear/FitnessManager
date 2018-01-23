package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "exercises")
public class Exercise extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference(value = "a_name")
    private Category category;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "exercise")
    @JsonManagedReference(value = "c_name")
    private Set<UserExercises> userExercisesSet;

    public Set<UserExercises> getUserExercisesSet() {
        return userExercisesSet;
    }

    public void setUserExercisesSet(Set<UserExercises> userExercisesSet) {
        this.userExercisesSet = userExercisesSet;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Exercise(Integer id, Category category, String name, String description) {
        super(id);
        this.category = category;
        this.name = name;
        this.description = description;
    }

    public Exercise() {
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
