package io.valentinsoare.fastwebserver;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.core.ResolvableType;

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

  /**
   * Get the bean instance supplier for 'serverFactoryCustomizer'.
   */
  private static BeanInstanceSupplier<WebServerFactoryCustomizer> getServerFactoryCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<WebServerFactoryCustomizer>forFactoryMethod(FastWebApplication.class, "serverFactoryCustomizer")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(FastWebApplication.class).serverFactoryCustomizer());
  }

  /**
   * Get the bean definition for 'serverFactoryCustomizer'.
   */
  public static BeanDefinition getServerFactoryCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebServerFactoryCustomizer.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(WebServerFactoryCustomizer.class, TomcatServletWebServerFactory.class));
    beanDefinition.setInstanceSupplier(getServerFactoryCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
