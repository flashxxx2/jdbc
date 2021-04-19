package tech.itpark.proggerhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class JavaConfiguration {
//  @Bean // viewResolver, messageSource
//  public ViewResolver viewResolver() {
//    return (viewName, locale) -> new MappingJackson2JsonView();
//  }

  @Bean
  public DataSource dataSource() throws NamingException {
//    final var initialContext = new InitialContext();
//    return (DataSource) initialContext.lookup("java:/comp/env/jdbc/db");
    return new JndiTemplate().lookup("java:/comp/env/jdbc/db", DataSource.class);
  }
}
