package org.springframework.boot.actuate.autoconfigure.context.properties;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpoint;
import org.springframework.boot.actuate.context.properties.ConfigurationPropertiesReportEndpointWebExtension;

/**
 * Bean definitions for {@link ConfigurationPropertiesReportEndpointAutoConfiguration}.
 */
@Generated
public class ConfigurationPropertiesReportEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'configurationPropertiesReportEndpointAutoConfiguration'.
   */
  public static BeanDefinition getConfigurationPropertiesReportEndpointAutoConfigurationBeanDefinition(
      ) {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesReportEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ConfigurationPropertiesReportEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'configurationPropertiesReportEndpoint'.
   */
  private static BeanInstanceSupplier<ConfigurationPropertiesReportEndpoint> getConfigurationPropertiesReportEndpointInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ConfigurationPropertiesReportEndpoint>forFactoryMethod(ConfigurationPropertiesReportEndpointAutoConfiguration.class, "configurationPropertiesReportEndpoint", ConfigurationPropertiesReportEndpointProperties.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ConfigurationPropertiesReportEndpointAutoConfiguration.class).configurationPropertiesReportEndpoint(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'configurationPropertiesReportEndpoint'.
   */
  public static BeanDefinition getConfigurationPropertiesReportEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesReportEndpoint.class);
    beanDefinition.setInstanceSupplier(getConfigurationPropertiesReportEndpointInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'configurationPropertiesReportEndpointWebExtension'.
   */
  private static BeanInstanceSupplier<ConfigurationPropertiesReportEndpointWebExtension> getConfigurationPropertiesReportEndpointWebExtensionInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ConfigurationPropertiesReportEndpointWebExtension>forFactoryMethod(ConfigurationPropertiesReportEndpointAutoConfiguration.class, "configurationPropertiesReportEndpointWebExtension", ConfigurationPropertiesReportEndpoint.class, ConfigurationPropertiesReportEndpointProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ConfigurationPropertiesReportEndpointAutoConfiguration.class).configurationPropertiesReportEndpointWebExtension(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'configurationPropertiesReportEndpointWebExtension'.
   */
  public static BeanDefinition getConfigurationPropertiesReportEndpointWebExtensionBeanDefinition(
      ) {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesReportEndpointWebExtension.class);
    beanDefinition.setInstanceSupplier(getConfigurationPropertiesReportEndpointWebExtensionInstanceSupplier());
    return beanDefinition;
  }
}
