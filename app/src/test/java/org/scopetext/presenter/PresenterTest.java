package org.scopetext.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.scopetext.database.dao.DBHelper;

/**
 * Unit tests for Presenter.java
 * Created by john.qualls on 8/25/2016.
 */
public class PresenterTest {
    private Presenter objUnderTest;
    DBHelper dbHelper;
    ToolbarManager toolbarManager;
    ScopeTextFragmentManager stFragManager;

    @Before
    public void mockSetup() {
        dbHelper = Mockito.mock(DBHelper.class);
        toolbarManager = Mockito.mock(ToolbarManager.class);
        stFragManager = Mockito.mock(ScopeTextFragmentManager.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDBHelperTest() {
        dbHelper = null;
        objUnderTest = new Presenter(dbHelper, toolbarManager, stFragManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullToolbarManagerTest() {
        toolbarManager = null;
        objUnderTest = new Presenter(dbHelper, toolbarManager, stFragManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSTFragManagerTest() {
        stFragManager = null;
        objUnderTest = new Presenter(dbHelper, toolbarManager, stFragManager);
    }
}
