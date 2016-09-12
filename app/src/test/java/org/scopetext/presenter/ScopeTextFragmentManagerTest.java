package org.scopetext.presenter;

import org.junit.Before;
import org.mockito.Mockito;
import org.scopetext.view.MainActivity;

/**
 * Unit tests for ScopeTextFragmentManager.java
 * Created by john.qualls on 8/14/2016.
 */
public class ScopeTextFragmentManagerTest {
    private ScopeTextFragmentManager objUnderTest;
    private MainActivity activity;

    @Before
    public void setup() {
        // Setup mocks
        activity = Mockito.mock(MainActivity.class);

        // Setup SUT
        objUnderTest = new ScopeTextFragmentManager(activity);
    }
}
