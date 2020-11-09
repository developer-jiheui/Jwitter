package com.jwt.jwitter.web.repository;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * All integration tests must extend this class in order to reuse database.
 * This class creates an empty database for testing
 * All database migrations are applied automatically
 */
abstract class DatabaseIntegrationTest {

    static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:11.3")
        .withDatabaseName("jwitter")
        .withUsername("testuser")
        .withPassword("pass")
        .withReuse(true);

    static {
        DatabaseIntegrationTest.container.start();
    }

    public static class Initializer
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(final ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                "spring.datasource.url=" + DatabaseIntegrationTest.container.getJdbcUrl(),
                "spring.datasource.username=" + DatabaseIntegrationTest.container.getUsername(),
                "spring.datasource.password=" + DatabaseIntegrationTest.container.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
