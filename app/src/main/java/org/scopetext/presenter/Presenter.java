package org.scopetext.presenter;

/**
 * The presenter component in the
 * <a href=http://antonioleiva.com/mvp-android/ "MVP">MVP</a> architecture.
 * Middle man between the view and model components.
 * Responsible for routing each UI interaction from view, to a specific piece of business logic,
 * and interacts with model if necessary.
 * Created by john.qualls on 8/24/2016.
 */
public class Presenter {
    private static Presenter presenter;
    /**
     * Needed for instance control
     */
    private Presenter() {}

    /**
     * Static factory method that provides instance control for this singleton class.
     */
    public static Presenter getInstance() {
        if(presenter != null) {
            presenter = new Presenter();
        }
        return presenter;
    }
}
