package org.springframework.boot.actuate.autoconfigure.management;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.management.ThreadDumpEndpoint;

/**
 * Bean definitions for {@link ThreadDumpEndpointAutoConfiguration}.
 */
@Generated
public class ThreadDumpEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'threadDumpEndpointAutoConfiguration'.
   */
  public static BeanDefinition getThreadDumpEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ThreadDumpEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ThreadDumpEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'dumpEndpoint'.
   */
  private static BeanInstanceSupplier<ThreadDumpEndpoint> getDumpEndpointInstanceSupplier() {
    return BeanInstanceSupplier.<ThreadDumpEndpoint>forFactoryMethod(ThreadDumpEndpointAutoConfiguration.class, "dumpEndpoint")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(ThreadDumpEndpointAutoConfiguration.class).dumpEndpoint());
  }

  /**
   * Get the bean definition for 'dumpEndpoint'.
   */
  public static BeanDefinition getDumpEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ThreadDumpEndpoint.class);
    beanDefinition.setInstanceSupplier(getDumpEndpointInstanceSupplier());
    return beanDefinition;
  }
}
