package org.springframework.boot.actuate.autoconfigure.system;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DiskSpaceHealthIndicatorProperties}.
 */
@Generated
public class DiskSpaceHealthIndicatorProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'diskSpaceHealthIndicatorProperties'.
   */
  public static BeanDefinition getDiskSpaceHealthIndicatorPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DiskSpaceHealthIndicatorProperties.class);
    beanDefinition.setInstanceSupplier(DiskSpaceHealthIndicatorProperties::new);
    return beanDefinition;
  }
}
