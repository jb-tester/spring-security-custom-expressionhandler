package com.mytests.spring.security.customExpressionHandler.securityConfigurations.data;

import org.springframework.data.repository.CrudRepository;

/**
 * *
 * <p>Created by irina on 7/12/2022.</p>
 * <p>Project: spring-security-custom-expressionhandler</p>
 * *
 */
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

    Employee findFirstById(int id);
}
