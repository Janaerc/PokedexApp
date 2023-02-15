package com.example.pokedexapp.data.model;

import java.io.Serializable;

public class HabilidadeDTO implements Serializable {
    private int id_habilidade;
    private String descricao_habilidade;
    private int id_pokemon;

    public int getId_habilidade() {
        return id_habilidade;
    }

    public void setId_habilidade(int id_habilidade) {
        this.id_habilidade = id_habilidade;
    }

    public String getDescricao_habilidade() {
        return descricao_habilidade;
    }

    public void setDescricao_habilidade(String descricao_habilidade) {
        this.descricao_habilidade = descricao_habilidade;
    }

    public int getId_pokemon() {
        return id_pokemon;
    }

    public void setId_pokemon(int id_pokemon) {
        this.id_pokemon = id_pokemon;
    }

    public HabilidadeDTO() {
    }

    public HabilidadeDTO(int id_habilidade, String descricao_habilidade, int id_pokemon) {
        this.id_habilidade = id_habilidade;
        this.descricao_habilidade = descricao_habilidade;
        this.id_pokemon = id_pokemon;
    }
}
