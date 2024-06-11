package org.springframework.boot.actuate.autoconfigure.scheduling;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.scheduling.ScheduledTasksEndpoint;

/**
 * Bean definitions for {@link ScheduledTasksEndpointAutoConfiguration}.
 */
@Generated
public class ScheduledTasksEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'scheduledTasksEndpointAutoConfiguration'.
   */
  public static BeanDefinition getScheduledTasksEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ScheduledTasksEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ScheduledTasksEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'scheduledTasksEndpoint'.
   */
  private static BeanInstanceSupplier<ScheduledTasksEndpoint> getScheduledTasksEndpointInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ScheduledTasksEndpoint>forFactoryMethod(ScheduledTasksEndpointAutoConfiguration.class, "scheduledTasksEndpoint", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ScheduledTasksEndpointAutoConfiguration.class).scheduledTasksEndpoint(args.get(0)));
  }

  /**
   * Get the bean definition for 'scheduledTasksEndpoint'.
   */
  public static BeanDefinition getScheduledTasksEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ScheduledTasksEndpoint.class);
    beanDefinition.setInstanceSupplier(getScheduledTasksEndpointInstanceSupplier());
    return beanDefinition;
  }
}
