package io.valentinsoare.fastweb;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link FastWebApplication}.
 */
@Generated
public class FastWebApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'fastWebApplication'.
   */
  public static BeanDefinition getFastWebApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FastWebApplication.class);
    beanDefinition.setTargetType(FastWebApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(FastWebApplication.class);
    beanDefinition.setInstanceSupplier(FastWebApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
