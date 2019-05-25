package org.smart4j.test.framework.dynamicproxytest;

import org.smart4j.test.framework.Hello;
import org.smart4j.test.framework.HelloImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    //封装前
//    private Object target;
//
//    public DynamicProxy(Object target ) {
//        this.target = target;
//
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        before();
//        Object result = method.invoke(target,args);
//        after();
//        return result;
//    }
//
//    private void after() {
//        System.out.println("after");
//    }
//
//    private void before() {
//        System.out.println("before");
//    }
    //封装后

    private Object target;

    public DynamicProxy(Object target ) {

        this.target = target;

    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

        @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args);
        after();
        return result;
    }

    private void after() {
        System.out.println("after");
    }

    private void before() {
        System.out.println("before");
    }


    public static void main (String[] args) {

        //封装前
//        Hello hello = new HelloImpl();
//
//        DynamicProxy dynamicProxy = new DynamicProxy(hello);
//
//        Hello helloproxy = (Hello) Proxy.newProxyInstance(
//                hello.getClass().getClassLoader(),
//                hello.getClass().getInterfaces(),
//                dynamicProxy
//        );
//        helloproxy.say("Jack");

        //封装后
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        Hello helloProxy = dynamicProxy.getProxy();

        helloProxy.say("Jack");

    }


}
