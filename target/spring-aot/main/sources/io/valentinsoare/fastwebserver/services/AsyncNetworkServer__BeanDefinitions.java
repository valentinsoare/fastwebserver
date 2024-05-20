package io.valentinsoare.fastwebserver.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AsyncNetworkServer}.
 */
@Generated
public class AsyncNetworkServer__BeanDefinitions {
  /**
   * Get the bean definition for 'asyncNetworkServer'.
   */
  public static BeanDefinition getAsyncNetworkServerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AsyncNetworkServer.class);
    beanDefinition.setInstanceSupplier(AsyncNetworkServer::new);
    return beanDefinition;
  }
}
