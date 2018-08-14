package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;

import java.util.Map;


/**
 * 遍历实现
 */
final class ImplScanner implements Util
{
    private BeanDefinitionRegistry registry;

    private ApplicationContext applicationContext;

    void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    ImplScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    /**
     * 为实现添加代理
     * @throws BeansException
     */
    void scanner() throws BeansException {
        Map<String, Object> objectMap = applicationContext.getBeansWithAnnotation(SetProxy.class);
        for (Object object : objectMap.values())
        {
            try {
                registerToSpring(object.getClass());
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * 注册到spring
     * @param type
     */
    private void registerToSpring(Class<?> type) throws InstantiationException, IllegalAccessException
    {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(type);
        GenericBeanDefinition definition =
                setGenericBeanDefinition(type,(GenericBeanDefinition) builder.getRawBeanDefinition(),applicationContext);
        definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        String typeName = nameKey(type);
        registry.registerBeanDefinition(typeName, definition);
    }

    /**
     * 名字key
     * @param type
     * @return
     */
    private String nameKey(Class<?> type)
    {
        String typeName = type.getSimpleName();
        char[] charName = typeName.toCharArray();
        if (charName[0] >= 'A' && charName[0] <= 'Z') {
            charName[0] = (char) (charName[0] + 32);
        }
        typeName = new String(charName);
        return typeName;
    }
}
