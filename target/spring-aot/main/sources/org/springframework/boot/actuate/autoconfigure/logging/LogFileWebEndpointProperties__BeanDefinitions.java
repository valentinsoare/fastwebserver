package org.springframework.boot.actuate.autoconfigure.logging;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LogFileWebEndpointProperties}.
 */
@Generated
public class LogFileWebEndpointProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'logFileWebEndpointProperties'.
   */
  public static BeanDefinition getLogFileWebEndpointPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LogFileWebEndpointProperties.class);
    beanDefinition.setInstanceSupplier(LogFileWebEndpointProperties::new);
    return beanDefinition;
  }
}
