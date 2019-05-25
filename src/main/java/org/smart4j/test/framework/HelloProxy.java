package org.smart4j.test.framework;

public class HelloProxy implements Hello {

    private Hello hello;

    private UserService userService;

    public HelloProxy() {
        hello = new HelloImpl();
    }

    @Override
    public void say(String name) {
        before();
        hello.say(name);
        userService.findById(1L);
        after();
    }

    public void before() {
        System.out.println("Before");
    }

    public void after() {
        System.out.println("After");
    }

    public static void main(String[] args) {
        Hello helloProxy = new HelloProxy();
        helloProxy.say("Jack");
    }

}
