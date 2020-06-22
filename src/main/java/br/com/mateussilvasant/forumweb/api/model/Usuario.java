package br.com.mateussilvasant.forumweb.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author mateussilva
 *
 */

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class Usuario
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    private Integer id;


    @NotBlank
    @Size(max = 45)
    @Column(name = "login",length = 45, nullable = false)    
    private String login;
    
    @NotBlank
    @Size(max = 45)
    @Column(name = "nome",length = 45, nullable = false)  
    private String nome;
    
    @NotBlank
    @Size(max = 12,min = 6)
    @Column(name = "senha",length = 12, nullable = false)  
    private String senha;
    
    @NotBlank
    @Email
    @Size(max = 45)
    @Column(name = "email",length = 45, nullable = false)  
    private String email;

    @JsonIgnore
    @Column(name = "pontos")
    private int pontos;

    @Valid
    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "usuario",fetch = FetchType.LAZY)
    private Set<Topico> topicos;

    

}
