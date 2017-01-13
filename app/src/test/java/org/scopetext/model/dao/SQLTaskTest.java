package org.scopetext.model.dao;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.fail;

/**
 * Unit tests for SQLTask.java Created by john.qualls on 12/21/2016.
 */

public class SQLTaskTest {
    private final static String ILLEGAL_ARG_MSG =
            "The expected IllegalArgumentException was never thrown";
    private SQLTask objUnderTest;
    @Mock private DBHelper DBHelper;

    @Before
    public void setup() {
        objUnderTest = new SQLTask();
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNoParameters() {
        objUnderTest.doInBackground();
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithOneParameter() {
        objUnderTest.doInBackground("param");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWith3Parameters() {
        objUnderTest.doInBackground("param", "param1", "param2");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNoDBHelperParam() {
        objUnderTest.doInBackground("param", "param1");
        fail(ILLEGAL_ARG_MSG);
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowExceptionWithNoSQLParam() {
        objUnderTest.doInBackground("param", "param1");
        fail(ILLEGAL_ARG_MSG);
    }
}
