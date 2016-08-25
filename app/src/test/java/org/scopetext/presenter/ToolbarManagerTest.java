package org.scopetext.presenter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.scopetext.view.MainActivity;

import static org.junit.Assert.assertNull;

/**
 * Unit tests for ToolbarManager.java
 * Created by john.qualls on 8/14/2016.
 */
public class ToolbarManagerTest extends BaseTest {
    private final int TOOLBAR_VIEW = 1;
    private ToolbarManager objUnderTest;
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Mockito.mock(MainActivity.class);
    }

    @Ignore
    @Test
    public void dontSetupToolbarWithNullActivityTest() {
        objUnderTest = ToolbarManager.getInstance(null, 0);
        assertNull(objUnderTest);
    }

    @Ignore
    @Test
    public void setupToolbarTest() {
        // mock setup
/*        resetSingleton();
        Toolbar toolbar = Mockito.mock(Toolbar.class);
        Mockito.when(activity.findViewById(TOOLBAR_VIEW)).thenReturn(toolbar);
        objUnderTest = ToolbarManager.getInstance(activity, TOOLBAR_VIEW);

        // Test
        Mockito.verify(activity).setSupportActionBar(toolbar);*/
    }
}
