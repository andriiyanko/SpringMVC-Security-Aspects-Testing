package com.andy.aspects;

import com.andy.exceptions.NotAuthorizedException;
import com.andy.model.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SecurityAspect {
    private ApplicationContext applicationContext;


    public void checkAuthorize(){
        Authorization authorization = applicationContext.getBean("userAuthorized", Authorization.class);
        if (!authorization.getAuthorized()) {
            throw new NotAuthorizedException("User is not authorized!");
        }
    }
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
