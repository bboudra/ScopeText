package org.scopetext.model.javabean;

import org.scopetext.model.javabean.type.ActionApp;
import org.scopetext.model.javabean.type.ExternalApp;

/**
 * Java bean that contains all properties of a Response.
 * Created by john.qualls on 9/24/2016.
 */
public class Response {
    ActionApp action;
    ExternalApp externalApp;

    /**
     * Response constructor.
     * @param action Which action to perform on the external application.
     * @param externalApp Which external app will be affected.
     */
    public Response(ActionApp action, ExternalApp externalApp) {
        this.action = action;
        this.externalApp = externalApp;
    }

    public ActionApp getAction() {
        return action;
    }

    public ExternalApp getExternalApp() {
        return externalApp;
    }

    public void setAction(ActionApp action) {
        this.action = action;
    }

    public void setExternalApp(ExternalApp externalApp) {
        this.externalApp = externalApp;
    }
}
