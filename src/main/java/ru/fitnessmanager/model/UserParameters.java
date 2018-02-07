package ru.fitnessmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_parameters")
public class UserParameters extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "e_name")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parameter_id", nullable = false)
    @JsonBackReference(value = "e_name")
    private Parameter parameter;

    @Column(name = "date")
    @NotNull
    private Date date;

    @Column(name = "value")
    private double value;

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

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UserParameters(Integer id, User user, Parameter parameter, Date date, double value) {
        super(id);
        this.user = user;
        this.parameter = parameter;
        this.date = date;
        this.value = value;
    }

    public UserParameters() {
    }

    @Override
    public String toString() {
        return "UserParameters{" +
                "user=" + user +
                ", parameter=" + parameter +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
