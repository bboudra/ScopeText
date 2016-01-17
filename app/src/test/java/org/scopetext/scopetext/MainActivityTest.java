package org.scopetext.scopetext;

import android.os.Bundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit tests for MainActivity.java
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MainActivity.class)
public class MainActivityTest {
    @Spy
    private MainActivity mainActivity;
    Bundle savedInstanceState;

    @Before
    public void setup() {
        savedInstanceState = Mockito.mock(Bundle.class);
        mainActivity = Mockito.spy(new MainActivity());

        //
    }

    @Test
    public void layoutTest() {
        mainActivity = new MainActivity();
        mainActivity.onCreate(savedInstanceState);

    }
}
