package org.scopetext.model.cache;

import org.scopetext.model.javabean.ScopeText;

import java.util.List;

/**
 * // TODO Implement thread safe cache ScopeTextCache contains the most up to date ScopeText objects
 * from the database. The cache is updated whenever a ScopeText is changed in the database. Created
 * by john.qualls on 11/29/2016.
 */
public class ScopeTextCache implements Cache<ScopeText> {
    private static final Cache cache = new ScopeTextCache();
    private List<ScopeText> scopeTexts;

    public static Cache getInstance() {
        return cache;
    }

    @Override
    public void updateCache(List<ScopeText> newValues) {
        scopeTexts = newValues;
    }

    @Override
    public List<ScopeText> getCache() {
        return scopeTexts;
    }

    /*
         * Updates the cache at a specific interval. The update to the map must be thread safe in order
         * to guarantee clients get the correct data.
         */
    private void updateCache() {

    }
}
