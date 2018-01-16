package ru.fitnessmanager.model;

import java.util.Date;

public class WeightControl extends BaseEntity {

    private User user;

    private Date date;

    private double weight;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public WeightControl(Integer id, User user, Date date, double weight) {
        super(id);
        this.user = user;
        this.date = date;
        this.weight = weight;
    }
}
