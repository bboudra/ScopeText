package org.scopetext.presenter;

import org.scopetext.model.dao.DBHelper;

/**
 * Unit tests for Presenter.java
 * Created by john.qualls on 8/25/2016.
 */
public class ScopeTextPresenterTest {
    private Presenter objUnderTest;
    DBHelper dbHelper;
    ToolbarManager toolbarManager;
    ScopeTextFragmentManager stFragManager;

    // TODO Refactor tests once presenter collaborators are refactored.
 /*   @Before
    public void mockSetup() {
        dbHelper = Mockito.mock(DBHelper.class);
        toolbarManager = Mockito.mock(ToolbarManager.class);
        stFragManager = Mockito.mock(ScopeTextFragmentManager.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDBHelperTest() {
        dbHelper = null;
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, stFragManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullToolbarManagerTest() {
        toolbarManager = null;
        objUnderTest = new copeTextPresenter(dbHelper, toolbarManager, stFragManager);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSTFragManagerTest() {
        stFragManager = null;
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, stFragManager);
    }

    @Test
    public void addFragmentOnStartupTest() {
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, stFragManager);
        Mockito.verify(stFragManager).addFragment(Matchers.any(ScopeTextListFragment.class));
    }*/
}
