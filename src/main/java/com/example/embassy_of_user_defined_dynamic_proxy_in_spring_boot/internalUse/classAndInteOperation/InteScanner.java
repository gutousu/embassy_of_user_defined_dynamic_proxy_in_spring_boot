package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.classAndInteOperation;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.AProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.Util;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;


/**
 * 接口扫描
 */
final class InteScanner extends ClassPathBeanDefinitionScanner implements Util
{
    private ApplicationContext applicationContext;

    void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        super.setResourceLoader(applicationContext);
    }

    InteScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 条件
     */
    public void registerDefaultFilters()
    {
        this.addIncludeFilter(new AnnotationTypeFilter(SetProxy.class));
    }

    /**
     *
     * @param basePackages
     * @return
     */
    public Set<BeanDefinitionHolder> doScan(String... basePackages)
    {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            try {
                GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
                Class<?> type = Class.forName(definition.getBeanClassName());
                SetProxy setProxy = type.getAnnotation(SetProxy.class);
                AProxy proxy = setProxy.value().newInstance();
                proxy.setMethodMap(methodMap(type));
                definition.getPropertyValues().add("type", type);
                definition.getPropertyValues().add("proxy", proxy);
                definition.setBeanClass(ProxyFactory.class);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return beanDefinitions;
    }

    /**
     * 条件
     * @param beanDefinition
     * @return
     */
    public boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().hasAnnotation(SetProxy.class.getName());
    }

    /**
     * 开始扫描
     * @param packageName 扫描包的路径
     */
    void scanner(String packageName)
    {
        super.scan(packageName);
    }

}
