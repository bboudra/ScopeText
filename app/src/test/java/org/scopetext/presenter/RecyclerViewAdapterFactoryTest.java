package org.scopetext.presenter;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.scopetext.presenter.fragment.FragmentAction;
import org.scopetext.presenter.fragment.ScopeTextFragment;
import org.scopetext.view.ScopeTextListFragment;

/**
 * Created by john.qualls on 4/4/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class RecyclerViewAdapterFactoryTest {
    @Mock
    FragmentAction fragmentAction;

    @Test(expected = NullPointerException.class)
    public void itShouldAssertExceptionForNullclassName() {
        RecyclerViewAdapterFactory.buildAdapter(null, fragmentAction);
        fail("An NullPointerException should have been thrown, but it was not.");
    }

    @Test(expected = NullPointerException.class)
    public void itShouldAssertExceptionForNullFragmentAction() {
        RecyclerViewAdapterFactory.buildAdapter(ScopeTextFragment.SCOPE_TEXT_LIST, null);
        fail("An NullPointerException should have been thrown, but it was not.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void itShouldAssertExceptionForInvalidclassName() {
        RecyclerViewAdapterFactory.buildAdapter(ScopeTextFragment.NEW_CONTACT, fragmentAction);
        fail("An IllegalArgumentException should have been thrown, but it was not.");
    }

    @Test
    public void itShouldAssertAdapterImplForScopeTextListFragment() {
        RecyclerViewAdapter result =
                RecyclerViewAdapterFactory.buildAdapter(ScopeTextFragment.SCOPE_TEXT_LIST, fragmentAction);
        assertTrue("Wrong RecyclerViewAdapter Implementation.",result instanceof ScopeTextAdapter);
    }
}
