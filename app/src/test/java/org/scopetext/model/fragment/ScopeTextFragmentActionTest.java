package org.scopetext.model.fragment;


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
import org.scopetext.view.NewContactFragment;
import org.scopetext.view.ScopeTextListFragment;

/**
 * Unit tests for ScopeTextFragmentAction.java
 * Created by john.qualls on 8/14/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ScopeTextFragmentActionTest {
    private ScopeTextFragmentAction objUnderTest;
    @Mock
    private MainActivity activity;
    @Mock
    private FragmentManager fragmentManager;
    @Mock
    private Fragment fragment;
    @Mock
    private FragmentTransaction fragmentTransaction;

    @Before
    public void setup() {
        // Mock setup
        Mockito.when(fragmentManager.beginTransaction()).thenReturn(fragmentTransaction);

        // SUT setup
        objUnderTest = new ScopeTextFragmentAction(fragmentManager);
    }

    @Test
    public void shouldAssertNullFragManagerForNullActivity() {
        // Setup Mocks
        Mockito.when(activity.getSupportFragmentManager()).thenReturn(fragmentManager);

        // Test
        objUnderTest = new ScopeTextFragmentAction(null);
        objUnderTest.activityRefresh(null);
        Assert.assertNull(objUnderTest.getFragmentManager());
    }

    @Test
    public void shouldAssertSameFragManagerAsNewActivity() {
        // Setup Mocks
        Mockito.when(activity.getSupportFragmentManager()).thenReturn(fragmentManager);

        // Test
        objUnderTest = new ScopeTextFragmentAction(null);
        objUnderTest.activityRefresh(activity);
        Assert.assertEquals("FragmentManager references are not the same.", fragmentManager,
                objUnderTest.getFragmentManager());
    }

    @Test
    public void shouldVerifyNoFragmentCommitForNullFragment() {
        fragment = null;
        objUnderTest.addFragment(0, fragment, ScopeTextFragment.SCOPE_TEXT_LIST);
        Mockito.verifyZeroInteractions(fragmentManager);
        Mockito.verifyZeroInteractions(fragmentTransaction);
    }

    @Test
    public void shouldVerifyAddForScopeTextListFragment() {
        fragment = Mockito.mock(ScopeTextListFragment.class);
        objUnderTest.addFragment(0, fragment, ScopeTextFragment.SCOPE_TEXT_LIST);
        Mockito.verify(fragmentTransaction).add(0, fragment,
                ScopeTextFragment.SCOPE_TEXT_LIST.getName());
    }

    @Test
    public void shouldVerifyReplaceForNonScopeTextListFragment() {
        fragment = Mockito.mock(NewContactFragment.class);
        objUnderTest.addFragment(0, fragment, ScopeTextFragment.NEW_CONTACT);
        Mockito.verify(fragmentTransaction).replace(0, fragment,
                ScopeTextFragment.NEW_CONTACT.getName());
        Mockito.verify(fragmentTransaction).addToBackStack(null);
    }
}
