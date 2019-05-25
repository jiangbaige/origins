package org.smart4j.framework.helper;

import org.smart4j.test.aop.util.CollectionUtil;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 *
 * @author
 * @since 1.0.0
 */
public class IocHelper {

    static{
        //获取所有的Bean类与Bean实例之间的映射关系(简称 Bean Map)

        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            //遍历 Bean Map
            for(Map.Entry<Class<?>,Object>beanEntry : beanMap.entrySet()) {
                //从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                //获取Bean类定义的所有成员变量(简称Bean Field)
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)) {
                    //遍历 Bean Field
                    for(Field beanField : beanFields) {
                        //判断当前 Bean Field 是否带有Inject注解
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            //在Bean Map中获取Bean Field 对应的实例
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = null;
                            try {
                                beanFieldInstance = beanField.get(beanFieldClass);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            if (beanFieldInstance != null) {
                                //通过反射初始化 BeanField 的值
                                ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }

}
