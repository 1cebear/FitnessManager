package ru.fitnessmanager.model;

public class Category extends BaseEntity{

    private String name;

    private String description;

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
}
