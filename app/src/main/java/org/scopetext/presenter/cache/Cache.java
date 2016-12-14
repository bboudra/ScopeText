package org.scopetext.presenter.cache;

import java.util.Map;

/**
 * Cache is used to cache any type of JavaBean object in ScopeText. The objects are stored in a
 * map data structure.
 * Created by john.qualls on 12/14/2016.
 */
public interface Cache {

    /**
     * Retrieves the cache.
     * @param <k> The key to retrieve a JavaBean object.
     * @param <v> A JavaBean object.
     * @return A reference to the cache.
     */
    public <k, v> Map<k, v> getCache();

    /**
     * Sets the new cache.
     * @param cache The new cache reference.
     * @param <k> The key to retrieve a JavaBean object
     * @param <v> A JavaBean object.
     */
    public <k, v> void setCache(Map<k, v> cache);
}
