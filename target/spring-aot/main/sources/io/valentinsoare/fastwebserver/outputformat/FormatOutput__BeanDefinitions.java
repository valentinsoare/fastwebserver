package io.valentinsoare.fastwebserver.outputformat;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FormatOutput}.
 */
@Generated
public class FormatOutput__BeanDefinitions {
  /**
   * Get the bean definition for 'formatOutput'.
   */
  public static BeanDefinition getFormatOutputBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FormatOutput.class);
    beanDefinition.setInstanceSupplier(FormatOutput::new);
    return beanDefinition;
  }
}
