package com.example.pokedexapp.backend;




import com.example.pokedexapp.data.model.LoginDTO;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PokedexService {

    @POST("login")
    Call<UsuarioDTO> login(@Body LoginDTO login);

    /*
    @GET("mutantes")
    Call<List<MutanteDTO>> getAllMutantes();

    @GET("mutantes/{id}")
    Call<MutanteDTO> getMutanteById(@Path("id") Long id);
    */
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
    @POST("Pokemons")
    Call<PokemonDTO> cadastrarPokemon(@Body PokemonDTO pokemonDTO);

    @GET("ProcurarTipo/{tipo}")
    Call<List<String>> procurarTipo(@Path("tipo") String tipo);
 /*
    @PUT("mutantes/{id}")
    Call<MutanteDTO> putMutante(@Path("id") Long id, @Body MutanteDTO mutanteDTO);

    @DELETE("mutantes/{id}")
    Call<MutanteDTO> deleteMutante(@Path("id") Long id);
 */
    @GET("usuarios/{id}")
    Call<UsuarioDTO> getUsuario(@Path("id") Long id);



}
