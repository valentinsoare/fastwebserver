package io.valentinsoare.fastwebserver.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ServerOptionsExecutionTime}.
 */
@Generated
public class ServerOptionsExecutionTime__BeanDefinitions {
  /**
   * Get the bean definition for 'serverOptionsExecutionTime'.
   */
  public static BeanDefinition getServerOptionsExecutionTimeBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ServerOptionsExecutionTime.class);
    beanDefinition.setInstanceSupplier(ServerOptionsExecutionTime::new);
    return beanDefinition;
  }
}
