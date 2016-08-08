package org.scopetext.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.scopetext.view.MainActivity.ScreenSlidePagerAdapter;

/**
 * Unit tests for MainActivity.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ MainActivity.class, Bundle.class})
public class MainActivityTest {
    private MainActivity mainActivity;
    Bundle savedInstanceState;

    @Before
    public void setup() {
        savedInstanceState = PowerMockito.mock(Bundle.class);
        mainActivity = Mockito.spy(new MainActivity());
    }

    @Test
    public void layoutTest() {
        FragmentManager fragmentManager = Mockito.mock(FragmentManager.class);
        ScreenSlidePagerAdapter pagerAdapter = Mockito.mock(ScreenSlidePagerAdapter.class);
        Toolbar toolbar = Mockito.mock(Toolbar.class);

        // Stubs
        try {
            Mockito.doNothing().when(mainActivity).setContentView(Matchers.anyInt());
            Mockito.doReturn(Mockito.mock(ViewPager.class)).
                    when(mainActivity).findViewById(R.id.fragment_pager);
            Mockito.doReturn(fragmentManager).when(mainActivity).getSupportFragmentManager();
            Mockito.doReturn(toolbar).when(mainActivity).
                    findViewById(R.id.actionBar);
            
            PowerMockito.whenNew(ScreenSlidePagerAdapter.class).
                    withArguments(fragmentManager).thenReturn(pagerAdapter);
        } catch (Exception e) {
            Assert.fail("Error setting up stubs: " + e.getMessage());
        }

        // Run method under test
        mainActivity.onCreate(savedInstanceState);

        // Test
        Mockito.verify(mainActivity).setContentView(R.layout.activity_main);
    }
}
