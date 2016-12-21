package org.scopetext.model.javabean;

/**
 * Java bean that contains all properties of a ScopeText. Created by john.qualls on 9/24/2016.
 */
public class ScopeText {
    private String name;
    private Message message;
    private Response response;
    private boolean inUse;

    /**
     * ScopeText constructor.
     *
     * @param name Name of the ScopeText.
     * @param message The message this ScopeText references.
     * @param response The response this ScopeText references.
     * @param inUse Whether or not this ScopeText is in use by the user.
     */
    public ScopeText(String name, Message message, Response response, boolean inUse) {
        this.name = name;
        this.message = message;
        this.response = response;
        this.inUse = inUse;
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
