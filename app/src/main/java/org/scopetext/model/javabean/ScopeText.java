package org.scopetext.model.javabean;

import android.database.Cursor;

/**
 * Java bean that contains all properties of a ScopeText.
 * Created by john.qualls on 9/24/2016.
 */
public class ScopeText {
    private String name;
    private Message message;
    private Response response;
    private boolean inUse;

    /**
     * Builds a ScopeText instance based on a database cursor.
     * @param cursor The database cursor.
     */
    public ScopeText(Cursor cursor){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
