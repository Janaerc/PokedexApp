package com.example.pokedexapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.UsuarioDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisarHabilidade extends AppCompatActivity {

    EditText pesquisaHabilidade;
    TextView resultado;
    UsuarioDTO usuarioDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_habilidade);

        pesquisaHabilidade = findViewById(R.id.editTxtPesquisar);
        resultado = findViewById(R.id.txtPesquisar);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        System.out.println("aqui em baixo o di do usuario");
        System.out.println(usuarioDTO.getId());
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public void pesquisar (View view) {

        String habilidadePoke = pesquisaHabilidade.getText().toString();
        if (habilidadePoke.isEmpty()) {
            Toast.makeText(this, "Informe o tipo", Toast.LENGTH_SHORT).show();
        } else {
            Call<List<String>> call1 = new RetrofitConfig().getPokedexService().procurarHabilidade(habilidadePoke);

            call1.enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>>call, Response<List<String>> response) {
                    System.out.println(response.body());
                    if (response.isSuccessful()) {
                        List<String> lista = response.body();
                        String interests = "";
                        for (String pokemonlista : lista) {
                            System.out.println(lista);
                            interests+="\n"+pokemonlista;
                        }
                        resultado.setText(interests);
                        Log.i("INFO", "Search result size:" + lista.size());
                        if (lista == null || lista.size() == 0) {
                            Toast.makeText(PesquisarHabilidade.this, "Nenhum Pokemon encontrado", Toast.LENGTH_SHORT).show();

                        } else {

                        }
                    } else {
                        Toast.makeText(PesquisarHabilidade.this, "Erro ao carregar Pokemons", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {
                    Toast.makeText(PesquisarHabilidade.this, "Erro de API", Toast.LENGTH_SHORT).show();
                }
            });
        }

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
                it.putExtra("usuario", usuarioDTO);
                startActivity(it);
                return true;

            case R.id.PesquisarTipo:
                it = new Intent( this, PesquisarTipo.class);
                it.putExtra("usuario", usuarioDTO);
                startActivity(it);
                return true;

            case R.id.PesquisarHabilidade:
                it = new Intent( this, PesquisarHabilidade.class);
                it.putExtra("usuario", usuarioDTO);
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