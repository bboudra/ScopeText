package org.scopetext.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Unit tests for ScopeTextFragmentManager.java
 * Created by john.qualls on 8/14/2016.
 */
@PrepareForTest(ScopeTextFragmentManager.class)
@RunWith(PowerMockRunner.class)
public class ScopeTextFragmentManagerTest {
    private ScopeTextFragmentManager objUnderTest;

    @Test
    public void returnNewInstanceTest() {
        try {
            // Mock setup
            ScopeTextFragmentManager mock = Mockito.mock(ScopeTextFragmentManager.class);
            PowerMockito.spy(ScopeTextFragmentManager.class);
            PowerMockito.whenNew(ScopeTextFragmentManager.class).withNoArguments().thenReturn(mock);

            // Test
            objUnderTest = ScopeTextFragmentManager.getInstance();
            assertEquals(mock, objUnderTest);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void returnReferenceTest() {
        objUnderTest = ScopeTextFragmentManager.getInstance();
        ScopeTextFragmentManager singletonReference = ScopeTextFragmentManager.getInstance();
        assertEquals(objUnderTest, singletonReference);
    }

    private void initializeSingleton() {
        try {
            objUnderTest = Whitebox.invokeConstructor(ScopeTextFragmentManager.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
    }
}
