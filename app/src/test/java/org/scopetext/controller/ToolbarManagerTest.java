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
 * Unit tests for ToolbarManager.java
 * Created by john.qualls on 8/14/2016.
 */
@PrepareForTest(ToolbarManager.class)
@RunWith(PowerMockRunner.class)
public class ToolbarManagerTest {
    private ToolbarManager objUnderTest;

    @Test
    public void returnNewInstanceTest() {
        try {
            // Mock setup
            ToolbarManager mock = Mockito.mock(ToolbarManager.class);
            PowerMockito.spy(ToolbarManager.class);
            PowerMockito.whenNew(ToolbarManager.class).withNoArguments().thenReturn(mock);

            // Test
            objUnderTest = ToolbarManager.getInstance();
            assertEquals(mock, objUnderTest);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void returnReferenceTest() {
        objUnderTest = ToolbarManager.getInstance();
        ToolbarManager singletonReference = ToolbarManager.getInstance();
        assertEquals(objUnderTest, singletonReference);
    }

    private void initializeSingleton() {
        try {
            objUnderTest = Whitebox.invokeConstructor(ToolbarManager.class);
        } catch(Exception e) {
            fail(e.getMessage());
        }
    }
}
