package ro.rosmof.model.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "ro.rosmof.model.repositories")
@ComponentScan(basePackages = "ro.rosmof.model")
@ImportResource("classpath:model-extra-configuration.xml")
public class ModelConfiguration {

    private Environment environment;

    @Autowired
    public ModelConfiguration(Environment environment) {
        this.environment = environment;
    }

    /**
     * <p>The datasource provided by hikari is much faster than the ones provided
     * in spring.</p>
     */
    @Bean
    public DataSource hikariDataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(environment.getProperty("mysql.diploma.url"));
        ds.setDriverClassName(environment.getProperty("db.mysql.driverClassName"));
        ds.setUsername(environment.getProperty("mysql.diploma.user"));
        ds.setPassword(environment.getProperty("mysql.diploma.password"));
        return ds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(hikariDataSource());
        bean.setPersistenceUnitName("localContainerEntity");
        bean.setJpaProperties(additionalProperties(environment));
        return bean;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager tx = new JpaTransactionManager();
        tx.setEntityManagerFactory(entityManagerFactory().getObject());
        return tx;
    }

    @Bean
    public SimpleTransactionProfile profile() {
        SimpleTransactionProfile profile = new SimpleTransactionProfile();
        profile.setOrder(1);
        return profile;
    }


    private Properties additionalProperties(Environment env) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.current_session_context_class", environment.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", environment.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
        properties.setProperty("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.format_sql", environment.getProperty("spring.jpa.properties.hibernate.format_sql"));
        return properties;
    }
}
