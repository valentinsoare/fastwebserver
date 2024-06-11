package org.springframework.boot.actuate.autoconfigure.logging;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.logging.LoggersEndpoint;
import org.springframework.boot.logging.LoggingSystem;

/**
 * Bean definitions for {@link LoggersEndpointAutoConfiguration}.
 */
@Generated
public class LoggersEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'loggersEndpointAutoConfiguration'.
   */
  public static BeanDefinition getLoggersEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LoggersEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(LoggersEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'loggersEndpoint'.
   */
  private static BeanInstanceSupplier<LoggersEndpoint> getLoggersEndpointInstanceSupplier() {
    return BeanInstanceSupplier.<LoggersEndpoint>forFactoryMethod(LoggersEndpointAutoConfiguration.class, "loggersEndpoint", LoggingSystem.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(LoggersEndpointAutoConfiguration.class).loggersEndpoint(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'loggersEndpoint'.
   */
  public static BeanDefinition getLoggersEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LoggersEndpoint.class);
    beanDefinition.setInstanceSupplier(getLoggersEndpointInstanceSupplier());
    return beanDefinition;
  }
}
