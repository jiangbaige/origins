package org.smart4j.test.framework.dynamicproxytest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.smart4j.test.framework.Hello;
import org.smart4j.test.framework.HelloImpl;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy instance = new CGLibProxy();

    private  CGLibProxy() {
    }

    public static CGLibProxy getInstance() {
        return instance;
    }
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls,this);

    }
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        before();
        Object result = proxy.invokeSuper(obj,args);
        after();
        return result;
    }


    private void before() {
    }

    private void after() {
    }

    public static void main (String[] args) {
//        CGLibProxy cgLibProxy = new CGLibProxy();
//        Hello helloProxy = cgLibProxy.getProxy(HelloImpl.class);
        Hello helloProxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
        helloProxy.say("Jack");
    }

}
