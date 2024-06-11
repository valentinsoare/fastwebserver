package org.springframework.boot.actuate.autoconfigure.logging;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LogFileWebEndpointAutoConfiguration}.
 */
@Generated
public class LogFileWebEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'logFileWebEndpointAutoConfiguration'.
   */
  public static BeanDefinition getLogFileWebEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LogFileWebEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(LogFileWebEndpointAutoConfiguration::new);
    return beanDefinition;
  }
}
