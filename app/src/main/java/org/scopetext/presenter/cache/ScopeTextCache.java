package org.scopetext.presenter.cache;

import org.scopetext.model.javabean.ScopeText;

import java.util.HashMap;
import java.util.Map;

/**
 * ScopeTextCache encapsulates the cache for the ScopeText JavaBean objects.
 * <pre>
 * NOTES:
 * 1. Not meant to be subclassed.
 * 2. Not thread safe.
 * 3. Singleton.
 * </pre>
 * Created by john.qualls on 12/14/2016.
 * @see Cache
 */

public class ScopeTextCache implements Cache {
    public static final ScopeTextCache singleton = new ScopeTextCache();
    private HashMap<String, ScopeText> cache;

    private ScopeTextCache() {}

    public static ScopeTextCache getinstance() {
        return singleton;
    }

    @Override
    public Map<String, ScopeText> getCache() {
        return cache;
    }

    @Override
    public void setCache(Map<String, ScopeText> cache) {
        this.cache = (HashMap<String, ScopeText>) cache;
    }
}
