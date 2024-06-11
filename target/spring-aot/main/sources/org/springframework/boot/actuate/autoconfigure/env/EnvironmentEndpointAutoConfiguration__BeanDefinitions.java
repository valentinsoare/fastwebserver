package org.springframework.boot.actuate.autoconfigure.env;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.env.EnvironmentEndpoint;
import org.springframework.boot.actuate.env.EnvironmentEndpointWebExtension;
import org.springframework.core.env.Environment;

/**
 * Bean definitions for {@link EnvironmentEndpointAutoConfiguration}.
 */
@Generated
public class EnvironmentEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'environmentEndpointAutoConfiguration'.
   */
  public static BeanDefinition getEnvironmentEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EnvironmentEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(EnvironmentEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'environmentEndpoint'.
   */
  private static BeanInstanceSupplier<EnvironmentEndpoint> getEnvironmentEndpointInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EnvironmentEndpoint>forFactoryMethod(EnvironmentEndpointAutoConfiguration.class, "environmentEndpoint", Environment.class, EnvironmentEndpointProperties.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(EnvironmentEndpointAutoConfiguration.class).environmentEndpoint(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'environmentEndpoint'.
   */
  public static BeanDefinition getEnvironmentEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EnvironmentEndpoint.class);
    beanDefinition.setInstanceSupplier(getEnvironmentEndpointInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'environmentEndpointWebExtension'.
   */
  private static BeanInstanceSupplier<EnvironmentEndpointWebExtension> getEnvironmentEndpointWebExtensionInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EnvironmentEndpointWebExtension>forFactoryMethod(EnvironmentEndpointAutoConfiguration.class, "environmentEndpointWebExtension", EnvironmentEndpoint.class, EnvironmentEndpointProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(EnvironmentEndpointAutoConfiguration.class).environmentEndpointWebExtension(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'environmentEndpointWebExtension'.
   */
  public static BeanDefinition getEnvironmentEndpointWebExtensionBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EnvironmentEndpointWebExtension.class);
    beanDefinition.setInstanceSupplier(getEnvironmentEndpointWebExtensionInstanceSupplier());
    return beanDefinition;
  }
}
