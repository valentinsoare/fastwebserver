package io.valentinsoare.fastweb.helpers;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ConnectionPool}.
 */
@Generated
public class ConnectionPool__BeanDefinitions {
  /**
   * Get the bean definition for 'connectionPool'.
   */
  public static BeanDefinition getConnectionPoolBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConnectionPool.class);
    beanDefinition.setInstanceSupplier(ConnectionPool::new);
    return beanDefinition;
  }
}
