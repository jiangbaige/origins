package org.smart4j.test.framework;

public class GreetingImpl implements Greeting {
    @Override
    public void sayHello(String name) {
        before();
        System.out.println("Hello!" + name);
        after();
    }

    private void before() {

        System.out.println("Before");
    }

    private void after() {

        System.out.println("After");
    }
}
