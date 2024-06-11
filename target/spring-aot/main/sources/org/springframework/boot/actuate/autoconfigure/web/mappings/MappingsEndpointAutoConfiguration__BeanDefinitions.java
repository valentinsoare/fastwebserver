package org.springframework.boot.actuate.autoconfigure.web.mappings;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.boot.actuate.web.mappings.servlet.DispatcherServletsMappingDescriptionProvider;
import org.springframework.boot.actuate.web.mappings.servlet.FiltersMappingDescriptionProvider;
import org.springframework.boot.actuate.web.mappings.servlet.ServletsMappingDescriptionProvider;
import org.springframework.context.ApplicationContext;

/**
 * Bean definitions for {@link MappingsEndpointAutoConfiguration}.
 */
@Generated
public class MappingsEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'mappingsEndpointAutoConfiguration'.
   */
  public static BeanDefinition getMappingsEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MappingsEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(MappingsEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mappingsEndpoint'.
   */
  private static BeanInstanceSupplier<MappingsEndpoint> getMappingsEndpointInstanceSupplier() {
    return BeanInstanceSupplier.<MappingsEndpoint>forFactoryMethod(MappingsEndpointAutoConfiguration.class, "mappingsEndpoint", ApplicationContext.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(MappingsEndpointAutoConfiguration.class).mappingsEndpoint(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'mappingsEndpoint'.
   */
  public static BeanDefinition getMappingsEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MappingsEndpoint.class);
    beanDefinition.setInstanceSupplier(getMappingsEndpointInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link MappingsEndpointAutoConfiguration.ServletWebConfiguration}.
   */
  @Generated
  public static class ServletWebConfiguration {
    /**
     * Get the bean definition for 'servletWebConfiguration'.
     */
    public static BeanDefinition getServletWebConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(MappingsEndpointAutoConfiguration.ServletWebConfiguration.class);
      beanDefinition.setInstanceSupplier(MappingsEndpointAutoConfiguration.ServletWebConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'servletMappingDescriptionProvider'.
     */
    private static BeanInstanceSupplier<ServletsMappingDescriptionProvider> getServletMappingDescriptionProviderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<ServletsMappingDescriptionProvider>forFactoryMethod(MappingsEndpointAutoConfiguration.ServletWebConfiguration.class, "servletMappingDescriptionProvider")
              .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MappingsEndpointAutoConfiguration.ServletWebConfiguration.class).servletMappingDescriptionProvider());
    }

    /**
     * Get the bean definition for 'servletMappingDescriptionProvider'.
     */
    public static BeanDefinition getServletMappingDescriptionProviderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ServletsMappingDescriptionProvider.class);
      beanDefinition.setInstanceSupplier(getServletMappingDescriptionProviderInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'filterMappingDescriptionProvider'.
     */
    private static BeanInstanceSupplier<FiltersMappingDescriptionProvider> getFilterMappingDescriptionProviderInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<FiltersMappingDescriptionProvider>forFactoryMethod(MappingsEndpointAutoConfiguration.ServletWebConfiguration.class, "filterMappingDescriptionProvider")
              .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MappingsEndpointAutoConfiguration.ServletWebConfiguration.class).filterMappingDescriptionProvider());
    }

    /**
     * Get the bean definition for 'filterMappingDescriptionProvider'.
     */
    public static BeanDefinition getFilterMappingDescriptionProviderBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(FiltersMappingDescriptionProvider.class);
      beanDefinition.setInstanceSupplier(getFilterMappingDescriptionProviderInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Bean definitions for {@link MappingsEndpointAutoConfiguration.ServletWebConfiguration.SpringMvcConfiguration}.
     */
    @Generated
    public static class SpringMvcConfiguration {
      /**
       * Get the bean definition for 'springMvcConfiguration'.
       */
      public static BeanDefinition getSpringMvcConfigurationBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(MappingsEndpointAutoConfiguration.ServletWebConfiguration.SpringMvcConfiguration.class);
        beanDefinition.setInstanceSupplier(MappingsEndpointAutoConfiguration.ServletWebConfiguration.SpringMvcConfiguration::new);
        return beanDefinition;
      }

      /**
       * Get the bean instance supplier for 'dispatcherServletMappingDescriptionProvider'.
       */
      private static BeanInstanceSupplier<DispatcherServletsMappingDescriptionProvider> getDispatcherServletMappingDescriptionProviderInstanceSupplier(
          ) {
        return BeanInstanceSupplier.<DispatcherServletsMappingDescriptionProvider>forFactoryMethod(MappingsEndpointAutoConfiguration.ServletWebConfiguration.SpringMvcConfiguration.class, "dispatcherServletMappingDescriptionProvider")
                .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MappingsEndpointAutoConfiguration.ServletWebConfiguration.SpringMvcConfiguration.class).dispatcherServletMappingDescriptionProvider());
      }

      /**
       * Get the bean definition for 'dispatcherServletMappingDescriptionProvider'.
       */
      public static BeanDefinition getDispatcherServletMappingDescriptionProviderBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(DispatcherServletsMappingDescriptionProvider.class);
        beanDefinition.setInstanceSupplier(getDispatcherServletMappingDescriptionProviderInstanceSupplier());
        return beanDefinition;
      }
    }
  }
}
