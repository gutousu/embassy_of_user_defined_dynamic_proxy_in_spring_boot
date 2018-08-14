package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Set;


/**
 * 遍历接口
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
     * 遍历符合条件的接口
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
                definition = setGenericBeanDefinition(type,definition,applicationContext);
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
     * 为接口添加代理
     * @param packageName 扫描包的路径
     */
    void scanner(String packageName)
    {
        super.scan(packageName);
    }
}
