package io.valentinsoare.fastwebserver.monitoringandalterting;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomMetricService}.
 */
@Generated
public class CustomMetricService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'customMetricService'.
   */
  private static BeanInstanceSupplier<CustomMetricService> getCustomMetricServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CustomMetricService>forConstructor(MeterRegistry.class)
            .withGenerator((registeredBean, args) -> new CustomMetricService(args.get(0)));
  }

  /**
   * Get the bean definition for 'customMetricService'.
   */
  public static BeanDefinition getCustomMetricServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CustomMetricService.class);
    beanDefinition.setInstanceSupplier(getCustomMetricServiceInstanceSupplier());
    return beanDefinition;
  }
}
