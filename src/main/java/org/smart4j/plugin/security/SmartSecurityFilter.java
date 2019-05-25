package org.smart4j.plugin.security;

import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;

/**
 * 安全过滤器
 *
 * @author
 *
 *
 * @since
 */

public class SmartSecurityFilter extends ShiroFilter {

    @Override
    public void init() throws Exception {
        super.init();
        WebSecurityManager webSecurityManager = super.getSecurityManager();
        //设置Realm，可同时支持多个 Realm，并按照先后顺序用逗号分割
        setRealms(webSecurityManager);
        //设置Cache，用于减少数据库查询次数，降低 I/O访问
        setCache(webSecurityManager);
    }


    private void setRealms(WebSecurityManager webSecurityManager) {
        //

        //
        //
        //
    }


    private void setCache(WebSecurityManager webSecurityManager) {
    }
}
