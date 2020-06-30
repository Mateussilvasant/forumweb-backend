package br.com.mateussilvasant.forumweb.api.dtos.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoResumo {

    private Integer id;

    private String titulo;

    private String conteudo;

    @JsonFormat(pattern="dd/MM/yyyy") 
    private LocalDateTime dataCriacao;

    private String nomeCriador;

    private int quantidadeComentarios;

}