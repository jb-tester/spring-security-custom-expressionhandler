package com.mytests.spring.security.customExpressionHandler.securityConfigurations;

import com.mytests.spring.security.customExpressionHandler.securityConfigurations.data.Employee;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * *
 * <p>Created by irina on 7/12/2022.</p>
 * <p>Project: spring-security-custom-expressionhandler</p>
 * *
 */
public class MyPrincipal implements UserDetails {

    Employee employee;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);

    public MyPrincipal(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        switch (employee.getSecLevel()) {
            case 1: {
                authorities.add(new SimpleGrantedAuthority("FULL_ACCESS"));
                authorities.add(new SimpleGrantedAuthority("AAA_FULL_ACCESS"));
                authorities.add(new SimpleGrantedAuthority("BBB_FULL_ACCESS"));
                authorities.add(new SimpleGrantedAuthority("AAA_USER_ACCESS"));
                authorities.add(new SimpleGrantedAuthority("BBB_USER_ACCESS"));
            }
            case 2:
                authorities.add(new SimpleGrantedAuthority(employee.getTeam() + "_FULL_ACCESS"));
            case 3:
                authorities.add(new SimpleGrantedAuthority(employee.getTeam() + "_USER_ACCESS"));
            case 4: {
                authorities.add(new SimpleGrantedAuthority("HR_ACCESS"));
                authorities.add(new SimpleGrantedAuthority(employee.getTeam() + "_USER_ACCESS"));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {

        return bCryptPasswordEncoder.encode(employee.getPassword());
    }

    @Override
    public String getUsername() {
        return employee.getFirstname() +"_" +employee.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return employee.toString();
    }
}
