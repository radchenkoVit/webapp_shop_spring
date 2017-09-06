package configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"system"})
@EnableTransactionManagement
public class DBConfiguration {

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql_scripts/create.sql")
                .addScript("sql_scripts/generate_data.sql")
                .build();
    }


//        // db stuff ---> TODO: checkout how it works, need to add creating db each time
//    @Bean
//    public DataSource dataSource(){
//        String DB_DRIVER = "org.h2.Driver";
//        String DB_URL = "jdbc:h2:~/gamingShop";
//        String DB_USERNAME = "root";
//        String DB_PASSWORD = "root";
//
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(DB_URL, DB_USERNAME, DB_PASSWORD);
//        driverManagerDataSource.setDriverClassName(DB_DRIVER);
//        return driverManagerDataSource;
//
//        //return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // optional in case of multiply packages to scan
        em.setPackagesToScan(new String[] { "system" }); //TODO: check this stuff
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean
    PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);

        return jpaTransactionManager;
    }


    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "false");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
