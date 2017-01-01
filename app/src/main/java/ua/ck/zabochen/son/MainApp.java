package ua.ck.zabochen.son;

import android.app.Application;
import android.os.AsyncTask;

import ua.ck.zabochen.son.controller.AnimalsController;
import ua.ck.zabochen.son.controller.TransportController;

public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Inflate Animals & Transport Lists
        new InflateListsTask().execute();
    }

    class InflateListsTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            AnimalsController.getInstance().setAnimals(getApplicationContext());
            TransportController.getInstance().setTransport(getApplicationContext());
            return null;
        }
    }

}
