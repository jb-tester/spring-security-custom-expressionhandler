package com.mytests.spring.security.customExpressionHandler;

import javax.persistence.*;
import java.util.Objects;

/**
 * *
 * <p>Created by irina on 7/12/2022.</p>
 * <p>Project: spring-security-custom-expressionhandler</p>
 * *
 */
@Entity
@Table(name="employees", schema = "security_tests")
public class Employees {

    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "team")
    private String team;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "salary")
    private Integer salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return id == employees.id && Objects.equals(firstName, employees.firstName) && Objects.equals(lastName, employees.lastName) && Objects.equals(team, employees.team) && Objects.equals(title, employees.title) && Objects.equals(salary, employees.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, team, title, salary);
    }
}
