package com.mytests.spring.security.customExpressionHandler.securityConfigurations;

import com.mytests.spring.security.customExpressionHandler.securityConfigurations.data.Employee;
import com.mytests.spring.security.customExpressionHandler.securityConfigurations.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Integer id = Integer.parseInt(username.substring(username.indexOf("_")+1));
        Employee user = repo.findFirstById(id);
        if (user==null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyPrincipal(user);
    }
}