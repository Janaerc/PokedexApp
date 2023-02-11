package com.example.pokedexapp;

//import static com.example.pokedexapp.backend.RequestTask.SHARED_PREFERENCES_NAME;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class Logout {

    private Activity activity;

    public Logout(Activity activity) {

        this.activity = activity;
    }

    public void logout() {
       /* SharedPreferences sharedPref = activity.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("is_logged_in");
        editor.remove("id");
        editor.apply();
        //finaliza o app
        activity.finishAffinity();*/
    }
}
