package org.smart4j.test.framework;

public class GreetingProxy implements Greeting {


    private GreetingImpl greetingImpl;

    public GreetingProxy(GreetingImpl greetingImpl) {
        this.greetingImpl = greetingImpl;
    }

    @Override
    public void sayHello(String name) {
        before();
        greetingImpl.sayHello(name);
        after();
    }

    public void before() {
        System.out.println("Before");
    }

    public void after() {
        System.out.println("After");
    }
}
