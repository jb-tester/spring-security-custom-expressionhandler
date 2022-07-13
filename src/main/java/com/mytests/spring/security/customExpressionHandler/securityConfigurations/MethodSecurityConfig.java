package com.mytests.spring.security.customExpressionHandler.securityConfigurations;

import com.mytests.spring.security.customExpressionHandler.securityConfigurations.MyMethodSecurityExpressionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        MyMethodSecurityExpressionHandler expressionHandler =
          new MyMethodSecurityExpressionHandler();
        return expressionHandler;
    }
}