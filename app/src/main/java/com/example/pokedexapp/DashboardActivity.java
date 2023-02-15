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
import com.example.pokedexapp.data.model.UsuarioDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    private final static int LOGIN = 1;

    UsuarioDTO usuarioDTO;
    TextView textViewTotalPokemon, TextViewHabilidades1, TextViewHabilidades2
            , TextViewHabilidades3, TextViewTipo2, TextViewTipo3, TextViewTipo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");

        textViewTotalPokemon = findViewById(R.id.textView5);
        TextViewHabilidades1 = findViewById(R.id.TxtHabilidade1);
        TextViewHabilidades2 = findViewById(R.id.TxtHabilidade2);
        TextViewHabilidades3 = findViewById(R.id.TxtHabilidade3);
        TextViewTipo1 = findViewById(R.id.txtTipo1);
        TextViewTipo2 = findViewById(R.id.txtTipo2);
        TextViewTipo3 = findViewById(R.id.txtTipo3);
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
        getTopHabilidades();
        getTopTipos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPokemonCount();
        getTopHabilidades();
        getTopTipos();
    }

    private void getPokemonCount(){
        System.out.println("entrou no getpokemoncount");
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

    private void getTopHabilidades(){
        Call<List<String>> call1 = new RetrofitConfig().getPokedexService().getTopHabilidades();
        call1.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> lista = response.body();
                if (lista.size() > 0)
                    TextViewHabilidades1.setText(lista.get(0));
                else
                    TextViewHabilidades1.setText("");
                if (lista.size() > 1)
                    TextViewHabilidades2.setText(lista.get(1));
                else
                    TextViewHabilidades2.setText("");
                if (lista.size() > 2)
                    TextViewHabilidades3.setText(lista.get(2));
                else
                    TextViewHabilidades3.setText("");
            }



            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }


    public void getTopTipos(){
        Call<List<String>> call1 = new RetrofitConfig().getPokedexService().getTopTipo();
        call1.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> lista = response.body();
                if (lista.size() > 0)
                    TextViewTipo1.setText(lista.get(0));
                else
                    TextViewTipo1.setText("");
                if (lista.size() > 1)
                    TextViewTipo2.setText(lista.get(1));
                else
                    TextViewTipo2.setText("");
                if (lista.size() > 2)
                    TextViewTipo3.setText(lista.get(2));
                else
                    TextViewTipo3.setText("");
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

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
                it.putExtra("usuario", usuarioDTO);
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


