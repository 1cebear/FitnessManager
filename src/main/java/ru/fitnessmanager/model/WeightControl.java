package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "weight_control")
public class WeightControl extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "d_name")
    private User user;

    @Column(name = "date")
    @NotNull
    private Date date;

    @Column(name = "weight")
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

    public WeightControl() {
    }

    @Override
    public String toString() {
        return "WeightControl{" +
                "user=" + user +
                ", date=" + date +
                ", weight=" + weight +
                '}';
    }
}