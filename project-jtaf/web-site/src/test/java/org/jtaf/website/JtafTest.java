package org.jtaf.website;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/application-context.xml")
public abstract class JtafTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);

    }

    @BeforeTransaction
    public void setupData() throws Exception {
        if (countRowsInTable("USERPROFILE") == 0) {
            executeSqlScript("classpath:sql/INSERT_USER_PROFILE.sql", false);
        }
    }

}
