package br.com.mateussilvasant.forumweb.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mateussilvasant.forumweb.api.configurations.repository.IRepository;
import br.com.mateussilvasant.forumweb.api.model.Topico;
import br.com.mateussilvasant.forumweb.api.model.Usuario;
    
public interface TopicoRepository extends IRepository<Topico,Integer> {


    @Query(value = "SELECT t FROM Topico AS t WHERE t.usuario = :criadorTopico")
	public Page<Topico> findAllTopicosByUsuario(Pageable pageable,@Param("criadorTopico") Usuario usuario);
    
}