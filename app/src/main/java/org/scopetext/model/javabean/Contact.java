package org.scopetext.model.javabean;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Java bean that contains all properties of a Contact.
 */
public class Contact {
    private String name;
    private boolean inList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInList() {
        return inList;
    }

    public void setInList(boolean inList) {
        this.inList = inList;
    }
}
