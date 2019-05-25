package org.smart4j.framework.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogProxy implements InvocationHandler {

    private Object target;

    public LogProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        return method.invoke(target, args);
    }

    @SuppressWarnings("unchecked")
    public <E> E getProxy(Class<E> e) {
        return (E) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    private void before() {
        System.out.println("aaa");
    }
}
