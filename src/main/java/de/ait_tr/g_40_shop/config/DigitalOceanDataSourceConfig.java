package de.ait_tr.g_40_shop.config;

import de.ait_tr.g_40_shop.domain.entity.Product;
import de.ait_tr.g_40_shop.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("fake-profile")
public class DigitalOceanDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DigitalOceanDataSourceConfig.class);

    @Value("${DB_USERNAME}")
    private String username;

    @Value("${DB_PASSWORD}")
    private String password;

    @Value("${DB_HOST}")
    private String hostname;

    @Value("${DB_PORT}")
    private String port;

    @Value("${DB_NAME}")
    private String database;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://" + hostname + ":" + port + "/" + database)
                .username(username)
                .password(password)
                .build();
    }

    public static void main(String[] args) {
        User user = new User();
        user.setPassword("111");
        user.setUsername("Test");
        Product product = new Product();
        product.setTitle("Prod");
        Object[] array = {user, product};
        
        logger.info("Array: {}", array);
    }
}