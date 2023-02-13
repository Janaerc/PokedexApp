package com.example.pokedexapp;

//import static com.example.pokedexapp.backend.RequestTask.SHARED_PREFERENCES_NAME;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.example.pokedexapp.backend.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private final static int LOGIN = 1;

    TextView textViewTotalPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textViewTotalPokemon = findViewById(R.id.textView5);
        /*SharedPreferences sharedPref = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        boolean isLoggedIn = sharedPref.getBoolean("is_logged_in", false);
        int id = sharedPref.getInt("id", -1);
        System.out.println("esse é o valor que ta passando de id:" + id);

        if (id == -1) {
            Intent loginIntent = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Login inválido", Toast.LENGTH_SHORT).show();
            startActivity(loginIntent);
            finish();
        }*/
    }
    @Override
    protected void onStart(){
        super.onStart();
        getPokemonCount();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPokemonCount();

    }

    private void getPokemonCount(){
        Call<Integer> call1 = new RetrofitConfig().getPokedexService().getCount();

        call1.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if(response.isSuccessful()) {
                    //log.i("INFO", "sucesso");
                    Integer qtd = response.body();
                    System.out.println(qtd);
                    textViewTotalPokemon.setText(String.format("%d", qtd));
                }


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Erro de API", Toast.LENGTH_SHORT).show();
                System.out.println(t.getMessage());
            }
        });
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


