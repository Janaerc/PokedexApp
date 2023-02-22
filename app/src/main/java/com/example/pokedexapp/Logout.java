package com.example.pokedexapp;

import android.app.Activity;
import android.content.Intent;


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
        editor.clear();
        editor.apply();*/
        //finaliza o app
        Intent intent = new Intent(activity, MainActivity.class);
        activity.finishAffinity();

        // Finaliza a atividade atual
        activity.finish();
        System.exit(0);

    }
}
