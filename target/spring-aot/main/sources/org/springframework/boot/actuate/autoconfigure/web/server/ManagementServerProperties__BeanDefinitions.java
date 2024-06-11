package org.springframework.boot.actuate.autoconfigure.web.server;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ManagementServerProperties}.
 */
@Generated
public class ManagementServerProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'managementServerProperties'.
   */
  public static BeanDefinition getManagementServerPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManagementServerProperties.class);
    beanDefinition.setInstanceSupplier(ManagementServerProperties::new);
    return beanDefinition;
  }
}
