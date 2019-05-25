package org.smart4j.framework;

import com.fasterxml.jackson.core.io.IOContext;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.ClassUtil;

/**
 * 加载相应的Helper类
 *
 * @author
 * @since 1.0.0
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName(),true);//要不要初始化
        }
    }
}
