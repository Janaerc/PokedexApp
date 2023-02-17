package com.example.pokedexapp;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexapp.adapter.PokemonListAdapter;
import com.example.pokedexapp.backend.ImageConverter;
import com.example.pokedexapp.backend.RetrofitConfig;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.data.model.UsuarioDTO;
import com.example.pokedexapp.helper.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarTodos extends AppCompatActivity {

    UsuarioDTO usuarioDTO;
    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;
    private List<PokemonDTO> pokemonDTOList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioDTO = (UsuarioDTO) getIntent().getSerializableExtra("usuario");
        setContentView(R.layout.activity_listar_todos2);

        recyclerView = findViewById(R.id.recyclerViewTodosPokemons);

/*
        Call<List<PokemonDTO>> call1 = new RetrofitConfig().getPokedexService().getAllPokemons();

        call1.enqueue(new Callback<List<PokemonDTO>>() {
            @Override
            public void onResponse(Call<List<PokemonDTO>> call, Response<List<PokemonDTO>> response) {
                List<PokemonDTO> lista = response.body();
                System.out.println(lista.get(0).getNome_pokemon());
                System.out.println(lista.get(1).getNome_pokemon());
                System.out.println(lista.get(2).getNome_pokemon());
                System.out.println(lista.get(3).getNome_pokemon());
                System.out.println(lista.get(4).getNome_pokemon());

                recyclerView.

            }

            @Override
            public void onFailure(Call<List<PokemonDTO>> call, Throwable t) {

            }
        });

*/





//Adicionar eventos de clique no recycler
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int id = pokemonDTOList.get(position).getId_pokemon();

                                id = 1;

                                Call<PokemonDTO> call1 = new RetrofitConfig().getPokedexService().getPokemonById(id);

                                call1.enqueue(new Callback<PokemonDTO>() {
                                    @Override
                                    public void onResponse(Call<PokemonDTO> call, Response<PokemonDTO> response) {
                                        if (response.isSuccessful()) {
                                            PokemonDTO selectedPokemonDTO = response.body();
                                            System.out.println(selectedPokemonDTO.getNome_pokemon());
                                            System.out.println("aqui em cima é no listartodos pegando por id");

                                            Bundle params = new Bundle();
                                            params.putString("operacao", "view");
                                            params.putSerializable("pokemon", selectedPokemonDTO);

                                            //IR PARA UMA ACTIVITY NOVA, NAO A CADASTRO POKEMON, APESAR DE SER PARECIDA
                                            Intent it = new Intent(ListarTodos.this, CadastroPokemon.class);
                                            it.putExtra("update", "update");
                                            it.putExtra("pokemon", selectedPokemonDTO);
                                            it.putExtra("usuario", usuarioDTO);
                                            startActivity(it);
                                        } else
                                            Toast.makeText(ListarTodos.this, "Erro ao recuperar Pokemon", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<PokemonDTO> call, Throwable t) {
                                        Toast.makeText(ListarTodos.this, "Erro de API:" + t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        })
        );

    }


    public void updateRecyclerPokemonList() {

        Call<List<PokemonDTO>> call1 = new RetrofitConfig().getPokedexService().getAllPokemons();

        call1.enqueue(new Callback<List<PokemonDTO>>() {
            @Override
            public void onResponse(Call<List<PokemonDTO>> call, Response<List<PokemonDTO>> response) {
                if (response.isSuccessful()) {
                    pokemonDTOList = response.body();

                    //configura adapter
                    pokemonListAdapter = new PokemonListAdapter(pokemonDTOList);

                    //configura recyclerView
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
                    recyclerView.setAdapter(pokemonListAdapter);
                } else
                    Toast.makeText(ListarTodos.this, "Erro ao carregar Pokemon", Toast.LENGTH_SHORT).show();
                Log.i("INFO", "erro");
            }

            @Override
            public void onFailure(Call<List<PokemonDTO>> call, Throwable t) {
                Toast.makeText(ListarTodos.this, "Erro de API", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        updateRecyclerPokemonList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRecyclerPokemonList();
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
    /*public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private String[] localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
       /* public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.textView);
            }

            public TextView getTextView() {
                return textView;
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
       /* public CustomAdapter(String[] dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);

            return new ViewHolder(view);
        }


    }


*/





