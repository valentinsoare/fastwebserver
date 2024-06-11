package org.springframework.boot.actuate.autoconfigure.condition;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Bean definitions for {@link ConditionsReportEndpointAutoConfiguration}.
 */
@Generated
public class ConditionsReportEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'conditionsReportEndpointAutoConfiguration'.
   */
  public static BeanDefinition getConditionsReportEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConditionsReportEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ConditionsReportEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'conditionsReportEndpoint'.
   */
  private static BeanInstanceSupplier<ConditionsReportEndpoint> getConditionsReportEndpointInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ConditionsReportEndpoint>forFactoryMethod(ConditionsReportEndpointAutoConfiguration.class, "conditionsReportEndpoint", ConfigurableApplicationContext.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ConditionsReportEndpointAutoConfiguration.class).conditionsReportEndpoint(args.get(0)));
  }

  /**
   * Get the bean definition for 'conditionsReportEndpoint'.
   */
  public static BeanDefinition getConditionsReportEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ConditionsReportEndpoint.class);
    beanDefinition.setInstanceSupplier(getConditionsReportEndpointInstanceSupplier());
    return beanDefinition;
  }
}
