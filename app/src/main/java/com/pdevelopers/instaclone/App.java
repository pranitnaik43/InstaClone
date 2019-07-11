package com.pdevelopers.instaclone;

import android.app.Application;

import com.parse.Parse;


public class App extends Application {
    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("4sO7am2l7H2bs7CuzXtYHQsyTCeyJmSx3r63eSbI")
                .clientKey("FrCyKdSGBq5pLjTKN5jxRVfJEsUVbEOMWShDkNRB")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
