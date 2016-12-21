package org.scopetext.model.cache;

import java.util.Map;

/**
 * Cache provides an interface for all types of cache in this application. Created by john.qualls on
 * 11/29/2016.
 */

public interface Cache {
    public <T> Map<String, T> getCache();
}
