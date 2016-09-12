package org.scopetext.presenter;

import android.support.v7.widget.Toolbar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.scopetext.view.MainActivity;

/**
 * Unit tests for ToolbarManager.java
 * Created by john.qualls on 8/14/2016.
 */
public class ToolbarManagerTest {
    private ToolbarManager objUnderTest;
    private MainActivity activity;
    private Toolbar toolbar;

    @Before
    public void setup(){
        // Mock collaborators
        activity = Mockito.mock(MainActivity.class);
        toolbar = Mockito.mock(Toolbar.class);

        // Create stubs
        Mockito.when(activity.findViewById(Matchers.anyInt())).thenReturn(toolbar);

        // Get SUT
        objUnderTest = new ToolbarManager(activity);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullActivityTest() {
        activity = null;
        new ToolbarManager(activity);
    }

    @Test(expected = NullPointerException.class)
    public void nullToolbar() {
        activity = Mockito.mock(MainActivity.class);
        Mockito.when(activity.findViewById(Matchers.anyInt())).thenReturn(null);
        new ToolbarManager(activity);
    }
}
