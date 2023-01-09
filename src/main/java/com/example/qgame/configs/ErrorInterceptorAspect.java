package com.example.qgame.configs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorInterceptorAspect {

    @AfterThrowing(pointcut = "execution(public * com.example.qgame..*(..))", throwing = "ex")
    public void errorInterceptor(JoinPoint joinPoint,Throwable ex) {
        // send email here
        System.out.println("exception in " + joinPoint.getSignature());
        System.out.println("exception message " + ex.getMessage());

    }

    @Before("execution(public * com.example.qgame.controllers.user.ForgetPasswordController.openForgetPasswordForm())")
    public void fsa(JoinPoint joinPoint) {

        System.out.println("sss");
    }
}
