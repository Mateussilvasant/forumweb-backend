package br.com.mateussilvasant.forumweb.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mateussilvasant.forumweb.api.configurations.repository.IRepository;
import br.com.mateussilvasant.forumweb.api.model.Comentario;
import br.com.mateussilvasant.forumweb.api.model.Topico;

public interface ComentarioRepository extends IRepository<Comentario,Integer>{


    @Query(value = "SELECT c FROM Comentario AS c where c.topico = :topico order by c.topico.id asc")
	List<Comentario> findAllComentariosByTopico(@Param("topico") Topico topico);

	long countByTopico(Topico topico);
    
}