package com.example.pokedexapp;

import static com.example.pokedexapp.backend.RequestTask.SHARED_PREFERENCES_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("is_logged_in", false);
        int id = sharedPref.getInt("id", -1);
        System.out.println("esse é o valor que ta passando de id:" + id);

        if (id == -1) {
            Intent loginIntent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Login inválido", Toast.LENGTH_SHORT).show();
            startActivity(loginIntent);
            finish();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {



            case R.id.CadastroPokemon:
                Intent it = new Intent( this, CadastroPokemon.class);
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
                Logout logout = new Logout(this);
                logout.logout();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

}




