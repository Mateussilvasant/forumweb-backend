package br.com.mateussilvasant.forumweb.api.dtos.response;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoCadastro extends RepresentationModel<TopicoCadastro>{

    private Integer id;
    private String titulo;

}