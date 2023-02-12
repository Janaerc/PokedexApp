package com.example.pokedexapp.data.model;

import java.io.Serializable;
import java.util.List;

public class PokemonDTO implements Serializable {


    private Long id;
    private String nome;
    private Long idUsuario;
    private List<HabilidadeDTO> habilidades;
    private String imagem;

    public PokemonDTO() {
        super();
    }

    public PokemonDTO(Long id, String nome, Long idUsuario, List<HabilidadeDTO> habilidades, String imagem) {
        super();
        this.id = id;
        this.nome = nome;
        this.idUsuario = idUsuario;
        this.habilidades = habilidades;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<HabilidadeDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadeDTO> habilidades) {
        this.habilidades = habilidades;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getImagem() {
        return imagem;
    }

    public void setFoto(String imagem) {
        this.imagem = imagem;
    }
}




