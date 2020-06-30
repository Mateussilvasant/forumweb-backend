package br.com.mateussilvasant.forumweb.api.model;

import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_topico")
    private Integer id;

    @NotBlank
    @Size(max = 100)
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @NotBlank
    @Size(max = 1500)
    @Column(name = "conteudo", length = 1500, nullable = false)
    private String conteudo;

    @Builder.Default
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnoreProperties(value = { "topicos" })
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Valid
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topico")
    private Set<Comentario> comentarios;

    @JsonIgnore
    public String getConteudoResumo() {

        String resultante = "";
        String[] paragrafos = conteudo.split("\n");
        int qtd = paragrafos.length;

        if (qtd > 4) {
            resultante = montarTexto(paragrafos, 0, 4);
        } else {
            resultante = montarTexto(paragrafos, 0, qtd);
        }

        return resultante;
    }

    private String montarTexto(String[] paragrafos, int inicio, int fim) {
        String resultante = "";

        for (int i = inicio; i < fim; i++) {
            resultante = resultante + paragrafos[i] + "\n";
        }

        return resultante;
    }

}
