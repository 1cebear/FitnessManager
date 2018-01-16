package ru.fitnessmanager.model;

public class BaseEntity {

    private Integer id;

    protected BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return (getId() == null) ? 0 : getId();
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), getId());
    }

    public boolean isNew() {
        return (getId() == null);
    }

}
