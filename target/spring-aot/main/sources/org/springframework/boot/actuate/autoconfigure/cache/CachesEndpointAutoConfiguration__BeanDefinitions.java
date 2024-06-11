package org.springframework.boot.actuate.autoconfigure.cache;

import java.util.Map;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.cache.CachesEndpoint;
import org.springframework.boot.actuate.cache.CachesEndpointWebExtension;

/**
 * Bean definitions for {@link CachesEndpointAutoConfiguration}.
 */
@Generated
public class CachesEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'cachesEndpointAutoConfiguration'.
   */
  public static BeanDefinition getCachesEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CachesEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(CachesEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'cachesEndpoint'.
   */
  private static BeanInstanceSupplier<CachesEndpoint> getCachesEndpointInstanceSupplier() {
    return BeanInstanceSupplier.<CachesEndpoint>forFactoryMethod(CachesEndpointAutoConfiguration.class, "cachesEndpoint", Map.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(CachesEndpointAutoConfiguration.class).cachesEndpoint(args.get(0)));
  }

  /**
   * Get the bean definition for 'cachesEndpoint'.
   */
  public static BeanDefinition getCachesEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CachesEndpoint.class);
    beanDefinition.setInstanceSupplier(getCachesEndpointInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'cachesEndpointWebExtension'.
   */
  private static BeanInstanceSupplier<CachesEndpointWebExtension> getCachesEndpointWebExtensionInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CachesEndpointWebExtension>forFactoryMethod(CachesEndpointAutoConfiguration.class, "cachesEndpointWebExtension", CachesEndpoint.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(CachesEndpointAutoConfiguration.class).cachesEndpointWebExtension(args.get(0)));
  }

  /**
   * Get the bean definition for 'cachesEndpointWebExtension'.
   */
  public static BeanDefinition getCachesEndpointWebExtensionBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CachesEndpointWebExtension.class);
    beanDefinition.setInstanceSupplier(getCachesEndpointWebExtensionInstanceSupplier());
    return beanDefinition;
  }
}
