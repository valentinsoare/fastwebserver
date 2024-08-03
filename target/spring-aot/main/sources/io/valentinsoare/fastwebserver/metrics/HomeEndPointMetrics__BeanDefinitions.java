package io.valentinsoare.fastwebserver.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link HomeEndPointMetrics}.
 */
@Generated
public class HomeEndPointMetrics__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'homeEndPointMetrics'.
   */
  private static BeanInstanceSupplier<HomeEndPointMetrics> getHomeEndPointMetricsInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HomeEndPointMetrics>forConstructor(MeterRegistry.class)
            .withGenerator((registeredBean, args) -> new HomeEndPointMetrics(args.get(0)));
  }

  /**
   * Get the bean definition for 'homeEndPointMetrics'.
   */
  public static BeanDefinition getHomeEndPointMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HomeEndPointMetrics.class);
    beanDefinition.setInstanceSupplier(getHomeEndPointMetricsInstanceSupplier());
    return beanDefinition;
  }
}
