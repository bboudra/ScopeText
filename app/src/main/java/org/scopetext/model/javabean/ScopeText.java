package org.scopetext.model.javabean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

/**
 * Java bean that contains all properties of a ScopeText. Created by john.qualls on 9/24/2016.
 */
public class ScopeText extends BaseObservable {
    private String name;
    private Message message;
    private Response response;
    private boolean inUse;
    private List<Contact> contacts;

    public ScopeText(){}

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

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Bindable
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Bindable
    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    @Bindable
    public List getContacts() {
        return contacts;
    }

    public void setContacts(List contact) {
        contacts = contact;
    }
}
