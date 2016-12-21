package org.scopetext.presenter.cache;

import java.util.Map;

/**
 * Cache is used to cache any type of JavaBean object in ScopeText. The objects are stored in a map
 * data structure.
 *
 * @param <k> The key to retrieve a JavaBean object.
 * @param <v> A JavaBean object. Created by john.qualls on 12/14/2016.
 */
public interface Cache<k, v> {

    /**
     * loads data from the database and stores it in memory.
     */
    public void load();

    /**
     * Stores the cache into the database.
     */
    public void store();

    /**
     * Retrieves the cache.
     *
     * @return A reference to the cache.
     */
    public Map<k, v> getCache();

    /**
     * Sets the new cache.
     *
     * @param cache The new cache reference.
     */
    public void setCache(Map<k, v> cache);
}
