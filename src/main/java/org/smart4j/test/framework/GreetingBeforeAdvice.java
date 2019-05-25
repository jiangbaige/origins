package org.smart4j.test.framework;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("Before");
    }
}
