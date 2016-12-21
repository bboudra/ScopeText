package org.scopetext.model.dao;

import android.os.AsyncTask;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.scopetext.view.MainActivity;

/**
 * Unit tests for DBHelperTask.java
 * Created by john.qualls on 12/21/2016.
 */

public class DBHelperTaskTest {
    private DBHelperTask objUnderTest;
    @Mock
    private MainActivity mainActivity;

    @Before
    public void setup() {
        objUnderTest = new DBHelperTask();
    }
}
