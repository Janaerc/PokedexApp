package com.example.pokedexapp.backend;




import com.example.pokedexapp.data.model.LoginDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PokedexService {

    @POST("login")
    Call<UsuarioDTO> login(@Body LoginDTO login);

    /*
    @GET("mutantes")
    Call<List<MutanteDTO>> getAllMutantes();

    @GET("mutantes/{id}")
    Call<MutanteDTO> getMutanteById(@Path("id") Long id);

    @GET("mutantes/count")
    Call<Integer> getCount();

    @GET("habilidades/top")
    Call<List<String>> getTopHabilities();

    @GET("mutantes/{hab}/habilidades")
    Call<List<MutanteDTO>> getMutantesByHabilidade(@Path("hab") String hab);

    @POST("mutantes")
    Call<MutanteDTO> postMutante(@Body MutanteDTO mutanteDTO);

    @PUT("mutantes/{id}")
    Call<MutanteDTO> putMutante(@Path("id") Long id, @Body MutanteDTO mutanteDTO);

    @DELETE("mutantes/{id}")
    Call<MutanteDTO> deleteMutante(@Path("id") Long id);

    @GET("usuarios/{id}")
    Call<UsuarioDTO> getUsuario(@Path("id") Long id);
     */
}
