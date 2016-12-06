package org.scopetext.presenter;

import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.scopetext.model.dao.DBHelper;

/**
 * Unit tests for Presenter.java
 * Created by john.qualls on 8/25/2016.
 */
public class ScopeTextPresenterTest {
    private Presenter objUnderTest;
    DBHelper dbHelper;
    ToolbarManager toolbarManager;
    FragmentAction fragmentAction;


    @Before
    public void mockSetup() {
        dbHelper = Mockito.mock(DBHelper.class);
        toolbarManager = Mockito.mock(ToolbarManager.class);
        fragmentAction = Mockito.mock(ScopeTextFragmentAction.class);
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test
    public void shouldVerifyNoActivityRefreshForNullActivity() {
        // Test
        objUnderTest.activityRefresh(null);
        Mockito.verifyZeroInteractions(fragmentAction);
    }

    @Test
    public void itShouldVerifyActivityRefresh() {
        // Mock Setup
        AppCompatActivity activity = Mockito.mock(AppCompatActivity.class);

        // Test
        objUnderTest.activityRefresh(activity);
        Mockito.verify(fragmentAction).activityRefresh(activity);
    }

    // TODO Refactor tests once presenter collaborators are refactored.
 /*   @Before
    public void mockSetup() {
        dbHelper = Mockito.mock(DBHelper.class);
        toolbarManager = Mockito.mock(ToolbarManager.class);
        fragmentAction = Mockito.mock(ScopeTextFragmentAction.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullDBHelperTest() {
        dbHelper = null;
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullToolbarManagerTest() {
        toolbarManager = null;
        objUnderTest = new copeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullSTFragManagerTest() {
        fragmentAction = null;
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
    }

    @Test
    public void addFragmentOnStartupTest() {
        objUnderTest = new ScopeTextPresenter(dbHelper, toolbarManager, fragmentAction);
        Mockito.verify(fragmentAction).addFragment(Matchers.any(ScopeTextListFragment.class));
    }*/
}
