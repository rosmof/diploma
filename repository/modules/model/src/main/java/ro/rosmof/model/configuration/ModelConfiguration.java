package ro.rosmof.model.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "ro.rosmof.model",
        transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = "ro.rosmof.model")
public class ModelConfiguration {

    private Environment environment;

    @Autowired
    public ModelConfiguration(Environment environment) {
        this.environment = environment;
    }

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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment environment) {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(hikariDataSource());
        bean.setJpaDialect(new HibernateJpaDialect());
        bean.setJpaVendorAdapter(adapter);
        bean.setPackagesToScan("ro.rosmof.model");
        bean.setJpaProperties(additionalProperties(environment));
        return bean;
    }


    @Bean
    @Qualifier(value = "diplomaTx")
    public JpaTransactionManager transactionManager(Environment environment) {
        return new JpaTransactionManager(entityManagerFactory(environment).getObject());
    }


    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties(Environment env) {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.setProperty("hibernate.current_session_context_class", env.getProperty("spring.jpa.properties.hibernate.current_session_context_class"));
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"));
        properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
        return properties;
    }
}
