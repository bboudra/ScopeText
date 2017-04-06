package org.scopetext.model.cache;

import java.util.List;

/**
 * Cache provides an interface for all types of cache in this application.
 * Created by john.qualls on 11/29/2016.
 */

public interface Cache <T> {
    public List<T> getCache();

    public void updateCache(List<T> newValues);
}
