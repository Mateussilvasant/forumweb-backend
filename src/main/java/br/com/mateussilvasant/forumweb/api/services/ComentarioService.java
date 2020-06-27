package br.com.mateussilvasant.forumweb.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mateussilvasant.forumweb.api.model.Comentario;
import br.com.mateussilvasant.forumweb.api.model.Topico;
import br.com.mateussilvasant.forumweb.api.repositories.ComentarioRepository;

@Service
public class ComentarioService {
    

     private ComentarioRepository repository;

     @Autowired
     public ComentarioService(ComentarioRepository repository){
         this.repository = repository;
     }

    /**
     * Realiza o cadastro do coemntario feito pelo usuário no Banco de Dados
     * @param comentario
     * @return O comentario salvo
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public Comentario cadastrarComentario(Comentario comentario) 
    {
        Comentario comentarioSalvo = repository.save(comentario);
        repository.refresh(comentarioSalvo);

        return comentarioSalvo;
    }

    /**
     * @param Topico em que o comentário é vinculado
     * @return retorna uma lista de comentários do tópico.
     */
    public List<Comentario> getListaComentarios(Topico topico)
    {
        return repository.findAllTopicosByComentario(topico);
    }

}