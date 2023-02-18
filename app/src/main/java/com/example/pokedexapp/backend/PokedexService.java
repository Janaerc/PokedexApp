package com.example.pokedexapp.backend;


import com.example.pokedexapp.data.model.LoginDTO;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PokedexService {

    @POST("login")
    Call<UsuarioDTO> login(@Body LoginDTO login);



    @GET("Pokemons")

    Call<List<PokemonDTO>> getAllPokemons();

    @GET("pokemon/{id}")
    Call<PokemonDTO> getPokemonById(@Path("id") int id);

    @GET("Pokemons/count")
    Call<Integer> getCount();

    @GET("habilidades/top")
    Call<List<String>> getTopHabilidades();

    @GET("tipos/top")
    Call<List<String>> getTopTipo();
    /*
    @GET("mutantes/{hab}/habilidades")
    Call<List<MutanteDTO>> getMutantesByHabilidade(@Path("hab") String hab);
    */
    @POST("PokemonCadastro")
    Call<PokemonDTO> cadastrarPokemon(@Body PokemonDTO pokemonDTO);

    @GET("ProcurarTipo/{tipo}")
    Call<List<String>> procurarTipo(@Path("tipo") String tipo);

    @GET("ProcurarHabilidade/{habilidade}")
    Call<List<String>> procurarHabilidade(@Path("habilidade") String habilidade);
 /*
    @PUT("mutantes/{id}")
    Call<MutanteDTO> putMutante(@Path("id") Long id, @Body MutanteDTO mutanteDTO);

    @DELETE("mutantes/{id}")
    Call<MutanteDTO> deleteMutante(@Path("id") Long id);
 */
    @GET("usuarios/{id}")
    Call<UsuarioDTO> getUsuario(@Path("id") Long id);

    @PUT("PokemonUpdate/{id}")
    Call<PokemonDTO> atualizaPokemon(@Path("id") int id, @Body PokemonDTO pokemon);

}
