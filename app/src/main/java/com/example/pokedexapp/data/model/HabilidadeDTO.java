package com.example.pokedexapp.data.model;


import java.io.Serializable;

public class HabilidadeDTO implements Serializable{

    private Long id;
    private String descricao;
    private Long idPokemon;

    public HabilidadeDTO() {
        super();
    }

    public HabilidadeDTO(Long id, String descricao, Long idPokemon) {
        super();
        this.idPokemon = idPokemon;
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdMutante() {
        return idPokemon;
    }

    public void setIdMutante(Long idMutante) {
        this.idPokemon = idMutante;
    }


}
