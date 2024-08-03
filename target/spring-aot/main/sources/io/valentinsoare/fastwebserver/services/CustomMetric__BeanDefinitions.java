package io.valentinsoare.fastwebserver.services;

import io.valentinsoare.fastwebserver.metrics.HomeEndPointMetrics;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomMetric}.
 */
@Generated
public class CustomMetric__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'customMetric'.
   */
  private static BeanInstanceSupplier<CustomMetric> getCustomMetricInstanceSupplier() {
    return BeanInstanceSupplier.<CustomMetric>forConstructor(HomeEndPointMetrics.class)
            .withGenerator((registeredBean, args) -> new CustomMetric(args.get(0)));
  }

  /**
   * Get the bean definition for 'customMetric'.
   */
  public static BeanDefinition getCustomMetricBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CustomMetric.class);
    beanDefinition.setInstanceSupplier(getCustomMetricInstanceSupplier());
    return beanDefinition;
  }
}
