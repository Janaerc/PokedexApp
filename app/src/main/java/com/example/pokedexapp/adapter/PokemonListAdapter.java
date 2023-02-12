package com.example.pokedexapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexapp.R;
import com.example.pokedexapp.data.model.PokemonDTO;
import com.example.pokedexapp.helper.ImageConverter;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {
    private final List<PokemonDTO> list;


    //private List<PokemonDTO> list;


    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView nome;
        ImageView imagem;

        public MyViewHolder(@NonNull View view){

            super(view);

            nome = view.findViewById(R.id.textViewNomePokemon);
            imagem = view.findViewById(R.id.imageViewPokemonAdapter);
        }
    }

    public PokemonListAdapter(List<PokemonDTO> list) {
             this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View pokemonItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cadastro_pokemon, parent, false);
        return new MyViewHolder(pokemonItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PokemonDTO pokemonDTO = list.get(position);
        holder.nome.setText(pokemonDTO.getNome());
        holder.imagem.setImageBitmap(ImageConverter.base64ToBitmap(pokemonDTO.getImagem()));
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }






}
