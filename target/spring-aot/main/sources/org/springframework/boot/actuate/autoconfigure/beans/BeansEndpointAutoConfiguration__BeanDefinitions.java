package org.springframework.boot.actuate.autoconfigure.beans;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.beans.BeansEndpoint;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Bean definitions for {@link BeansEndpointAutoConfiguration}.
 */
@Generated
public class BeansEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'beansEndpointAutoConfiguration'.
   */
  public static BeanDefinition getBeansEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BeansEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(BeansEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'beansEndpoint'.
   */
  private static BeanInstanceSupplier<BeansEndpoint> getBeansEndpointInstanceSupplier() {
    return BeanInstanceSupplier.<BeansEndpoint>forFactoryMethod(BeansEndpointAutoConfiguration.class, "beansEndpoint", ConfigurableApplicationContext.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(BeansEndpointAutoConfiguration.class).beansEndpoint(args.get(0)));
  }

  /**
   * Get the bean definition for 'beansEndpoint'.
   */
  public static BeanDefinition getBeansEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BeansEndpoint.class);
    beanDefinition.setInstanceSupplier(getBeansEndpointInstanceSupplier());
    return beanDefinition;
  }
}
