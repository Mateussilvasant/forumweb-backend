package br.com.mateussilvasant.forumweb.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author mateussilva
 *
 */

 @Builder
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
 @Entity
 @Table(name = "topico")
public class Topico
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_topico")
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "titulo",length = 100, nullable = false )    
    private String titulo;

    @NotBlank
    @Size(max = 1500)
    @Column(name = "conteudo",length = 1500, nullable = false )    
    private String conteudo;


    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonBackReference
    @JsonIgnoreProperties({
		"topicos",
	})
    @ManyToOne
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario usuario;

    
    @Valid
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "topico",fetch = FetchType.LAZY)
    private Set<Comentario> comentarios;

    


}
