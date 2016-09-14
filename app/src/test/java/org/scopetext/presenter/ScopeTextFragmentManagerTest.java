package org.scopetext.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.view.MainActivity;

/**
 * Unit tests for ScopeTextFragmentManager.java
 * Created by john.qualls on 8/14/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextFragmentManagerTest {
    private ScopeTextFragmentManager objUnderTest;
    @Mock
    private MainActivity activity;
    @Mock
    private FragmentManager fragManagerMock;
    @Mock
    private Fragment fragment;

    @Before
    public void setup() {
        // Setup Stubs
        Mockito.when(activity.getSupportFragmentManager()).thenReturn(fragManagerMock);

        // Setup SUT
        objUnderTest = new ScopeTextFragmentManager(activity);
    }

    @Test
    public void fragmentManagerTest(){
        Mockito.verify(activity).getSupportFragmentManager();
    }

    @Test
    public void addFragmentNullTest(){
        fragment = null;
        Assert.assertFalse("A null fragment should not try to be added to the FragmentManager.",
                objUnderTest.addFragment(fragment));
    }


    @Test
    public void addFragmentValidTest() {
        // Setup mock and stub
        FragmentTransaction fragTransMock = Mockito.mock(FragmentTransaction.class);
        Mockito.when(fragManagerMock.beginTransaction()).thenReturn(fragTransMock);
        Mockito.when(fragTransMock.commit()).thenReturn(1);

        // Test
        Assert.assertTrue("A valid Fragment should be added into the FragmentManager.",
                objUnderTest.addFragment(fragment));
    }
}
