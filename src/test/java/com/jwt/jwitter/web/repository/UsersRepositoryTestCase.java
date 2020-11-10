package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.User;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Test User Repository.
 * Use this class as a template for integration classes
 * You need {@link ExtendWith} in order to include custom config
 * You need @{@link JdbcTest} in order to run database migrations
 * You need {@link ContextConfiguration} to provide your custom spring config
 */
@ExtendWith(SpringExtension.class)
@JdbcTest
@ContextConfiguration(classes = UsersRepositoryTestCase.UserRepositoryConfig.class, initializers = DatabaseIntegrationTest.Initializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsersRepositoryTestCase extends DatabaseIntegrationTest {

    @Autowired
    private UsersRepository repository;

    /**
     * You can specify which sql script to run before this test.
     * In this case , 'create_test_user.sql' will be executed
     */
    @Test
    @Sql("/create_test_user.sql")
    @DisplayName("Execute sql script and checks if user with email exists")
    void testGetEmail() {
        final String email = "test@gmail.com";
        final User user = this.repository.getUserByEmail(email);
        Assertions.assertEquals(
            email,
            user.getEmail(),
            "Test that user from database has specific email"
        );
    }

    @TestConfiguration
    public static class UserRepositoryConfig {

        @Bean
        public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }

        @Bean
        public UsersRepository repository() {
            return new UsersRepository();
        }

        @Bean
        public AvatarUrlProvider avatarUrlProvider() {
            return Mockito.mock(AvatarUrlProvider.class);
        }

    }

}
