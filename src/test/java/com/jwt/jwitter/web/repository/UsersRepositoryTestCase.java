package com.jwt.jwitter.web.repository;

import com.jwt.jwitter.models.User;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

/**
 * Test User Repository.
 * Use this class as a template for integration classes
 */
@SpringBootTest
@ContextConfiguration(initializers = DatabaseIntegrationTest.Initializer.class)
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
        Assert.assertEquals(
            "Test that user from database has specific email",
            email,
            user.getEmail()
        );
    }

}
