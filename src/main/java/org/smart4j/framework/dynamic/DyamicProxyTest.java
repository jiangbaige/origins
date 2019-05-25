package org.smart4j.framework.dynamic;

import org.smart4j.test.framework.UserService;
import org.smart4j.test.framework.UserServiceImpl;

public class DyamicProxyTest {

    public static void main(String[] args) {
        LogProxy logProxy = new LogProxy(new UserServiceImpl());
        UserService userService = logProxy.getProxy(UserService.class);
        userService.findById(1L);
    }

}
