package com.jwt.jwitter.mapper;

import com.jwt.jwitter.avatars.AvatarUrlProvider;
import com.jwt.jwitter.models.User;
import com.jwt.jwitter.models.mappers.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.sql.ResultSet;

/**
 * Unit test for user mapper.
 */
public class UserMapperUnitTest {

    private UserMapper mapper;

    @Test
    void testMapper() throws Exception{
        this.mapper = new UserMapper(new AvatarUrlProvider("test",8080));
        final ResultSet set = Mockito.mock(ResultSet.class);
        final int id = 100;
        Mockito.when(set.getInt("id")).thenReturn(id);
        Mockito.when(set.getString(Mockito.anyString())).thenReturn("Jiheui");
        Mockito.when(set.getDate(Mockito.anyString())).thenReturn(new Date(System.currentTimeMillis()));

        final User user = this.mapper.mapRow(set,-1);
        Assertions.assertEquals(
                id,
                user.getId()
        );
    }
}
