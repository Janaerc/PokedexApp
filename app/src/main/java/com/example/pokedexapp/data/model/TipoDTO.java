package com.example.pokedexapp.data.model;

import java.io.Serializable;

public class TipoDTO implements Serializable {


    private Long idTipo;
    private String descricao;
    private Long idPokemon;

    public TipoDTO() {
        super();
    }

    public TipoDTO(Long idTipo, String descricao, Long idPokemon) {
        super();
        this.idPokemon = idPokemon;
        this.idTipo = idTipo;
        this.descricao = descricao;
    }

    public Long getId() {
        return idTipo;
    }

    public void setId(Long id) {
        this.idTipo = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Long idPokemon) {
        this.idPokemon = idPokemon;
    }


}









