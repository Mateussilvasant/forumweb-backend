package br.com.mateussilvasant.forumweb.api.dtos;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component("topico_dto")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDTO extends RepresentationModel<TopicoDTO>{

    private Integer id;
    private String titulo;
    private String dataCriacao;

}