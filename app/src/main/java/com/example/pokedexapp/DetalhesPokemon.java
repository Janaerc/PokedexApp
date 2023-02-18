package com.example.pokedexapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedexapp.backend.ImageConverter;
import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesPokemon extends AppCompatActivity {

    private static final int GALERIA = 200;
    String base64, update = "nada";
    Bitmap bitmap;

    UsuarioDTO usuarioDTO;
    PokemonDTO pokemonDTO;
    EditText nomePokemon, tipoPokemon, habilidadePokemon;
    Button buttonSalvar, buttonCamera, buttonGaleria;
    ImageView imageView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pokemon);

        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        pokemonDTO = (PokemonDTO) getIntent().getSerializableExtra("pokemon");

        nomePokemon = findViewById(R.id.editTextNameDetalhes);
        tipoPokemon = findViewById(R.id.editTextTipoDetalhes);
        habilidadePokemon = findViewById(R.id.editTextHabilidadeDetalhes);
        imageView = findViewById(R.id.fotoPokemonDetalhes);

        nomePokemon.setText(pokemonDTO.getNome_pokemon());
        tipoPokemon.setText(pokemonDTO.getTipo_pokemon());
        habilidadePokemon.setText(pokemonDTO.getHabilidade());
        imageView.setImageBitmap(ImageConverter.base64ToBitmap(pokemonDTO.getFoto_pokemon()));


    }

    public void atualizar(View view) {
        PokemonDTO pokemon = new PokemonDTO();
        pokemon.setId_usuario(usuarioDTO.getId());
        pokemon.setNome_pokemon(nomePokemon.getText().toString());
        pokemon.setTipo_pokemon(tipoPokemon.getText().toString());
        pokemon.setHabilidade(habilidadePokemon.getText().toString());
        pokemon.setFoto_pokemon(pokemonDTO.getFoto_pokemon());

        Call<PokemonDTO> call1 = new RetrofitConfig().getPokedexService().atualizaPokemon(pokemon.getId_usuario(),pokemon);
        System.out.println(pokemon.getId_usuario());
        System.out.println(pokemon.getNome_pokemon());
        System.out.println(pokemon.getTipo_pokemon());
        System.out.println(pokemon.getHabilidade());

        call1.enqueue(new Callback<PokemonDTO>() {
            @Override
            public void onResponse(Call<PokemonDTO> call, Response<PokemonDTO> response) {

            }

            @Override
            public void onFailure(Call<PokemonDTO> call, Throwable t) {

            }
        });




    }

}