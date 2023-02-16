package com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pokedexapp.adapter.PokemonListAdapter;
import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.PokemonDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisarTipo extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText pesquisaTipo;
    private PokemonListAdapter pokemonListAdapter;
    private List<PokemonDTO> pokemonDTOList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_tipo);

        pesquisaTipo = findViewById(R.id.pesquisarTipo);
        recyclerView = findViewById(R.id.recyclerPesquisaPokemons);

    }


    public void pesquisar (View view) {

        String tipoPoke = pesquisaTipo.getText().toString();
        if (tipoPoke.isEmpty()) {
            Toast.makeText(this, "Informe o tipo", Toast.LENGTH_SHORT).show();
        } else {
            Call<List<PokemonDTO>> call1 = new RetrofitConfig().getPokedexService().procurarTipo(tipoPoke);

            call1.enqueue(new Callback<List<PokemonDTO>>() {
                @Override
                public void onResponse(Call<List<PokemonDTO>>call, Response<List<PokemonDTO>> response) {
                    System.out.println(response.body());
                    if (response.isSuccessful()) {
                        List<PokemonDTO> lista = response.body();
                        Log.i("INFO", "Search result size:" + lista.size());
                        if (lista == null || lista.size() == 0) {
                            Toast.makeText(PesquisarTipo.this, "Nenhum Pokemon encontrado", Toast.LENGTH_SHORT).show();

                        } else {
                            //configura adapter
                            pokemonListAdapter = new PokemonListAdapter(lista);

                            //configura recyclerView
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                            recyclerView.setAdapter(pokemonListAdapter);
                        }
                    } else {
                        Toast.makeText(PesquisarTipo.this, "Erro ao carregar Pokemons", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<PokemonDTO>> call, Throwable t) {
                    Toast.makeText(PesquisarTipo.this, "Erro de API", Toast.LENGTH_SHORT).show();
                }
            });
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