package org.scopetext.model.javabean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

import java.util.List;

/**
 * Java bean that contains all properties of a ScopeText.
 * Created by john.qualls on 9/24/2016.
 */
public class ScopeText extends BaseObservable {
    private String name;
    private Message message;
    private Response response;
    private boolean inUse;
    private List<Contact> contacts;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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

    @Bindable
    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
        notifyPropertyChanged(BR.inUse);
    }

    @Bindable
    public List getContacts() {
        return contacts;
    }

    public void setContacts(List contact) {
        contacts = contact;
        notifyPropertyChanged(BR.contacts);
    }
}
