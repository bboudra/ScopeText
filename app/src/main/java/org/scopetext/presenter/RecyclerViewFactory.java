package org.scopetext.presenter;

/**
 * Initializes RecyclerView implementations based on specific class names.
 * Created by john.qualls on 4/3/2017.
 */

public final class RecyclerViewFactory {
    private final static RecyclerViewFactory recyclerViewFactory = new RecyclerViewFactory();

    private RecyclerViewFactory() {
    }
   /*
    * Used for unit testing this singleton class. Params are used to mock out collaborators with
    * this class.
    */
    RecyclerViewFactory(RecyclerViewFactory mockFactory) {

    }

    public static RecyclerViewFactory getInstance() {
        return recyclerViewFactory;
    }


}
