package ru.fitnessmanager.model;

public class Exercise extends BaseEntity{

    private Category category;

    private String name;

    private String description;

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

    @Override
    public String toString() {
        return "Exercise{" +
                "category=" + category +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
