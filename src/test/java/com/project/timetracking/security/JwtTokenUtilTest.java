package com.project.timetracking.security;

import com.project.timetracking.model.enums.Role;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import org.assertj.core.util.DateUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
class JwtTokenUtilTest {
    private static final String TEST_LOGIN = "testLogin";
    private static final String TEST_EMAIL = "test@mail.com";
    private static final String TEST_NAME = "testName";
    private static final String TEST_SURNAME = "testSurname";
    private static final String TEST_PASSWORD = "$2a$12$1csu.WUotB897TFyeMSjfuZuPYqD9JfY0ZhYkZFU10NwItsWNjQU6";
    private static final Role TEST_ROLE = Role.USER;
    private static final boolean TEST_ACTIVE = true;
    private JwtUser jwtUser;

    @Mock
    private JwtUserFactory jwtUserFactory;

    @InjectMocks
    JwtTokenUtil jwtTokenUtil;

    @Mock
    private Clock timeProviderMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        jwtUser = new JwtUser(1L, TEST_LOGIN, TEST_NAME, TEST_SURNAME, TEST_EMAIL, TEST_ACTIVE, TEST_PASSWORD,
                ReflectionTestUtils.invokeMethod(jwtUserFactory, "mapToGrantedAuthorities", TEST_ROLE));
        ReflectionTestUtils.setField(jwtTokenUtil, "expiration", 3600L);
        ReflectionTestUtils.setField(jwtTokenUtil, "globalExpiration", 86400L);
        ReflectionTestUtils.setField(jwtTokenUtil, "secret", "testSuperSecret");
    }

    @Test
    void getUsernameFromToken() {
        when(timeProviderMock.now()).thenReturn(DateUtil.now());
        final String token = createTestToken();
        Assert.assertEquals(TEST_LOGIN, jwtTokenUtil.getUsernameFromToken(token));
    }

    @Test
    public void getExpirationDateOfToken() {
        final Date now = DateUtil.now();
        when(timeProviderMock.now()).thenReturn(now);
        final String token = createTestToken();
        final Date expirationDateFromToken = jwtTokenUtil.getExpirationDateFromToken(token);
        assertEquals(DateUtil.timeDifference(expirationDateFromToken, now), 3600000L, 2000L);
    }

    @Test
    public void getCreatedDateOfToken() throws Exception {
        final Date now = DateUtil.now();
        when(timeProviderMock.now()).thenReturn(now);
        final String token = createTestToken();
        assertEquals(now.getTime(), jwtTokenUtil.getIssuedAtDateFromToken(token).getTime(), 3000L);
    }

    @org.junit.Test(expected = ExpiredJwtException.class)
    public void expiredTokenCannotBeRefreshed() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.yesterday());
        String token = createTestToken();
        jwtTokenUtil.canTokenBeRefreshed(token);
    }

    @Test
    public void notExpiredCanBeRefreshed() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now());
        String token = jwtTokenUtil.generateToken(jwtUser);
        assertTrue(jwtTokenUtil.canTokenBeRefreshed(token));
    }

    @Test
    public void testGeneratingTokensForDifferentCreationDates() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.yesterday())
                .thenReturn(DateUtil.now());
        final String token = createTestToken();
        final String laterToken = createTestToken();
        assertNotEquals(token, laterToken);
    }

    @Test
    public void TokenRefreshTest() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now())
                .thenReturn(DateUtil.tomorrow());
        String firstToken = createTestToken();
        String refreshedToken = jwtTokenUtil.refreshToken(firstToken);
        Date firstTokenDate = jwtTokenUtil.getExpirationDateFromToken(firstToken);
        Date refreshedTokenDate = jwtTokenUtil.getExpirationDateFromToken(refreshedToken);
        assertTrue(firstTokenDate.before(refreshedTokenDate));
    }

    @Test
    public void TokenValidationTest() {
        when(timeProviderMock.now())
                .thenReturn(DateUtil.now())
                .thenReturn(DateUtil.now());
        String token = createTestToken();
        assertTrue(jwtTokenUtil.validateToken(token, jwtUser));
    }

    @AfterEach
    public void destroy() {
        jwtUser = null;
        jwtTokenUtil = null;
        jwtUserFactory = null;
        timeProviderMock = null;
    }

    private String createTestToken() {
        return jwtTokenUtil.generateToken(jwtUser);
    }
}