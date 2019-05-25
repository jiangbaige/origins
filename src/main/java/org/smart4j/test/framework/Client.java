package org.smart4j.test.framework;


import org.springframework.aop.framework.ProxyFactory;

public class Client {
    public static void main(String[] args) {
//        Greeting greetingProxy = new GreetingProxy(new GreetingImpl());
//        greetingProxy.sayHello("Jack");
//        Greeting greeting = CGLibDynamicProxy.getInstance().getProxy(GreetingImpl.class);
//        greeting.sayHello("Jack");
        ProxyFactory proxyFactory = new ProxyFactory(); //创建代理工厂
        proxyFactory.setTarget(new GreetingImpl()); //射入目标类对象
        proxyFactory.addAdvice(new GreetingBeforeAdvice()); //添加前置增强
        proxyFactory.addAdvice(new GreetingAfterAdvice()); //添加后置增强
        proxyFactory.addAdvice(new GreetingBeforeAndAfterAdvice());
        proxyFactory.addAdvice(new GreetingAroundAdvice());

        Greeting greeting = (Greeting) proxyFactory.getProxy();//从代理工厂中获取代理
        greeting.sayHello("Jack");//调用代理的方法


    }
}
