package com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DashboardActivity extends AppCompatActivity {


    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences sharedPref = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("is_logged_in", false);
        if (!isLoggedIn) {
            Intent loginIntent = new Intent(this, MainActivity.class);


            startActivity(loginIntent);
            finish();
        }
        Intent it = getIntent();
        id = it.getStringExtra("id");

        System.out.println(id);
        System.out.println("CHEGOU AQUI NO ONCREATE");





    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {



            case R.id.CadastroPokemon:
                Intent it = new Intent( this, CadastroPokemon.class);
                it.putExtra("id", id);
                startActivity(it);

                return true;

            case R.id.ListarTodos:
                it = new Intent( this, ListarTodos.class);
                startActivity(it);
                return true;

            case R.id.PesquisarTipo:
                 it = new Intent( this, PesquisarTipo.class);
                startActivity(it);
                return true;

            case R.id.PesquisarHabilidade:
                 it = new Intent( this, PesquisarHabilidade.class);
                startActivity(it);
                return true;

            case R.id.Sair:
                // it = new Intent( this, .class);
                //startActivity(it);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

}




