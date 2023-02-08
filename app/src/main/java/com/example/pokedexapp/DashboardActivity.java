package com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);
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



            case R.id.ListarTodos:
                it = new Intent( this, ListarTodos.class);
                startActivity(it);

            case R.id.PesquisarTipo:
                 it = new Intent( this, PesquisarTipo.class);
                startActivity(it);
            case R.id.PesquisarHabilidade:
                 it = new Intent( this, PesquisarHabilidade.class);
                startActivity(it);
            case R.id.Sair:
                // it = new Intent( this, .class);
                //startActivity(it);


            default:
                return super.onOptionsItemSelected(item);
        }

    }

}




