package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * bean注册与加工
 */
@Component
final class BeanDefinitionRegistryPostProcessorImpl implements ApplicationContextAware,BeanDefinitionRegistryPostProcessor
{
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * bean注册
     * @param registry
     * @throws BeansException
     */
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ImplScanner implScanner = new ImplScanner(registry);
        implScanner.setApplicationContext(applicationContext);
        implScanner.scanner();
    }

    /**
     * bean加工
     * @param beanFactory
     * @throws BeansException
     */
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException
    {
        String packageName = getPackage();
        if (packageName == null)
            return;
        InteScanner inteScanner = new InteScanner((BeanDefinitionRegistry) beanFactory);
        inteScanner.setApplicationContext(applicationContext);
        inteScanner.scanner(packageName);
    }

    /**
     * 获取包路径
     * @return
     */
    private String getPackage()
    {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTraceElements[stackTraceElements.length - 1];
        String className = stackTraceElement.getClassName();
        String packageName = null;
        try {
            Class<?> type = Class.forName(className);
            packageName = type.getPackage().getName();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return packageName;
    }
}
