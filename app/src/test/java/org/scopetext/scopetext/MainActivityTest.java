package org.scopetext.scopetext;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import junit.framework.Assert;

import org.scopetext.scopetext.MainActivity.ScreenSlidePagerAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Matchers;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit tests for MainActivity.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MainActivity.class)
public class MainActivityTest {
    private MainActivity mainActivity;
    Bundle savedInstanceState;

    @Before
    public void setup() {
        savedInstanceState = Mockito.mock(Bundle.class);
        mainActivity = Mockito.spy(new MainActivity());
    }

    @Test
    public void layoutTest() {
        FragmentManager fragmentManager = Mockito.mock(FragmentManager.class);
        ScreenSlidePagerAdapter pagerAdapter = Mockito.mock(ScreenSlidePagerAdapter.class);

        // Stubs
        try {
            Mockito.doNothing().when(mainActivity).setContentView(R.layout.activity_main);
            Mockito.doReturn(Mockito.mock(ViewPager.class)).
                    when(mainActivity).findViewById(R.id.fragment_pager);
            Mockito.doReturn(fragmentManager).when(mainActivity).getSupportFragmentManager();
            PowerMockito.whenNew(ScreenSlidePagerAdapter.class).
                    withArguments(fragmentManager).thenReturn(pagerAdapter);
        } catch (Exception e) {
            Assert.fail("Error setting up stubs: " + e.getMessage());
        }

        // Run method under test
        mainActivity.onCreate(savedInstanceState);

        // Test
    }
}
