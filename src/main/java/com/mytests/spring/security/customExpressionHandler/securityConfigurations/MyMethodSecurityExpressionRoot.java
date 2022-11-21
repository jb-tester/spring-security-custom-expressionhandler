package com.mytests.spring.security.customExpressionHandler.securityConfigurations;

import com.mytests.spring.security.customExpressionHandler.securityConfigurations.data.Employee;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class MyMethodSecurityExpressionRoot
  extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
    private Object filterObject;
    private Object returnObject;
    public MyMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean isTeamMember(String team) {
        Employee user = ((MyPrincipal) this.getPrincipal()).getEmployee();
        return user.getTeam().equals(team);
    }
    public Boolean hasFirstLevelSecurity(){
        Employee user = ((MyPrincipal) this.getPrincipal()).getEmployee();
        return user.getSecLevel()==1;
    }
    public boolean hasAccessByTeamAndLevel(String team, int level){
        Employee user = ((MyPrincipal) this.getPrincipal()).getEmployee();
        return user.getTeam().equals(team)&&user.getSecLevel()<=level;
    }
    @Override
    public void setFilterObject(Object filterObject) {
       this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}