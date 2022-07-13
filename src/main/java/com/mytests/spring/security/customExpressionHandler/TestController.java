package com.mytests.spring.security.customExpressionHandler;

import com.mytests.spring.security.customExpressionHandler.securityConfigurations.MyPrincipal;
import com.mytests.spring.security.customExpressionHandler.securityConfigurations.data.Employee;
import com.mytests.spring.security.customExpressionHandler.securityConfigurations.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.MediaType.*;

/**
 * *
 * <p>Created by irina on 7/12/2022.</p>
 * <p>Project: spring-security-custom-expressionhandler</p>
 * *
 */
@RestController
public class TestController {
    @Autowired
    EmployeesRepository repo;

    @PreAuthorize("hasAccessByTeamAndLevel(#team,4)")  // method defined in the custom SecurityExpressionRoot
    @PostMapping(path = "/addEmployee/{team}", consumes = "application/json")
    public Employees add(@RequestBody Employees e, @PathVariable String team){

       repo.save(e);
        return e;
    }
    @PreAuthorize("hasAnyAuthority('AAA_FULL_ACCESS','BBB_FULL_ACCESS')")  // authorities defined in the custom MyPrincipal
    @GetMapping("/test1")
    public String test1(Principal user){
        return "test1 for : available if user has authorities 'AAA_FULL_ACCESS' or 'BBB_FULL_ACCESS';"+
                "current user is "+user.toString();
    }
    @PreAuthorize("isTeamMember('AAA')")
    @GetMapping("/test2")
    public String test2(Principal user){
        return "test2: available if user is from AAA team; current user is "+user.toString();
    }
    @PreAuthorize("hasFirstLevelSecurity()")
    @GetMapping("/test3")
    public ResponsePOJO test3(Principal user){
        ResponsePOJO rezult = new ResponsePOJO("test3: available for 1-level users", user);
        return rezult;
    }
    @PreAuthorize("hasAccessByTeamAndLevel(#team,#level)")
    @GetMapping("/test4/{team}/{level}")
    public String test4(@PathVariable String level, @PathVariable String team, Principal user){
        return "test4: available if the user is from team "+team+" and has security level "+level
                +"; current user is "+user.toString();
    }

    public  class ResponsePOJO{
        String message;
        Principal principal;

        public ResponsePOJO(String message, Principal principal) {
            this.message = message;
            this.principal = principal;
        }

        public String getMessage() {
            return message;
        }

        public Principal getPrincipal() {
            return principal;
        }
    }
}
