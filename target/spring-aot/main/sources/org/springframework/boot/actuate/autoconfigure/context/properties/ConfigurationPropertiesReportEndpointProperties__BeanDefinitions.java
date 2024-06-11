package org.springframework.boot.actuate.autoconfigure.context.properties;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ConfigurationPropertiesReportEndpointProperties}.
 */
@Generated
public class ConfigurationPropertiesReportEndpointProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'configurationPropertiesReportEndpointProperties'.
   */
  public static BeanDefinition getConfigurationPropertiesReportEndpointPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConfigurationPropertiesReportEndpointProperties.class);
    beanDefinition.setInstanceSupplier(ConfigurationPropertiesReportEndpointProperties::new);
    return beanDefinition;
  }
}
