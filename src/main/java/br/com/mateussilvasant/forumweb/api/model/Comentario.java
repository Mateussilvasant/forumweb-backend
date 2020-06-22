package br.com.mateussilvasant.forumweb.api.model;

import javax.persistence.Entity;

/**
 * @author mateussilva
 *
 */
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



 @Builder
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Entity
 @Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_comentario")
    private Integer id;

    @NotBlank
    @Size(max = 300)
    @Column(name = "texto_comentario",length = 300, nullable = false )    
    private String conteudo;

    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    @JsonIgnoreProperties({
		"comentarios",
	})
    @ManyToOne
    @JoinColumn(name = "id_topico",nullable = false)
    private Topico topico;

    
    @NotNull
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

}
