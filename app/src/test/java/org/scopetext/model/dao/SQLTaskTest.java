package org.scopetext.model.dao;

import org.junit.Before;
import org.mockito.Mock;
import org.scopetext.view.MainActivity;

/**
 * Unit tests for SQLTask.java
 * Created by john.qualls on 12/21/2016.
 */

public class SQLTaskTest {
    private SQLTask objUnderTest;
    @Mock
    private DBHelper DBHelper;

    @Before
    public void setup() {
        objUnderTest = new SQLTask();
    }
}
