package com.mytests.spring.security.customExpressionHandler.securityConfigurations.data;

import javax.persistence.*;
import java.util.Objects;

/**
 * *
 * <p>Created by irina on 7/12/2022.</p>
 * <p>Project: spring-security-custom-expressionhandler</p>
 * *
 */
@Entity
@Table(name = "employee", schema = "security_tests")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "firstname")
    private String firstname;
    @Basic
    @Column(name = "secondname")
    private String secondname;
    @Basic
    @Column(name = "team")
    private String team;
    @Basic
    @Column(name = "emp_role")
    private String empRole;
    @Basic
    @Column(name = "sec_level")
    private Integer secLevel;
    @Basic
    @Column(name = "password")
    private String password;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getEmpRole() {
        return empRole;
    }

    public void setEmpRole(String empRole) {
        this.empRole = empRole;
    }

    public Integer getSecLevel() {
        return secLevel;
    }

    public void setSecLevel(Integer secLevel) {
        this.secLevel = secLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id && Objects.equals(firstname, employee.firstname) && Objects.equals(secondname, employee.secondname) && Objects.equals(team, employee.team) && Objects.equals(empRole, employee.empRole) && Objects.equals(secLevel, employee.secLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, secondname, team, empRole, secLevel);
    }

    @Override
    public String toString() {
        return
                firstname + "_"+ id+
                ", team='" + team + '\'' +
                ", secLevel=" + secLevel ;
    }
}
