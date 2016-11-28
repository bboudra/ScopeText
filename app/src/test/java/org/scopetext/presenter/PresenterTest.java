package org.scopetext.presenter;

import android.database.sqlite.SQLiteDatabase;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.scopetext.model.dao.DBHelper;
import org.scopetext.model.dao.ScopeTextDAO;
import org.scopetext.model.javabean.ScopeText;
import org.scopetext.view.ScopeTextListFragment;

import java.util.ArrayList;

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

    @Test
    public void addFragmentOnStartupTest() {
        objUnderTest = new Presenter(dbHelper, toolbarManager, stFragManager);
        Mockito.verify(stFragManager).addFragment(Matchers.any(ScopeTextListFragment.class));
    }
}
