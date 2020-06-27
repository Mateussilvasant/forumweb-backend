package br.com.mateussilvasant.forumweb.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.mateussilvasant.forumweb.api.model.Topico;
import br.com.mateussilvasant.forumweb.api.model.Usuario;
import br.com.mateussilvasant.forumweb.api.model.enums.EPontos;
import br.com.mateussilvasant.forumweb.api.repositories.TopicoRepository;
import br.com.mateussilvasant.forumweb.api.repositories.UsuarioRepository;

@Service
public class TopicoService {

    private TopicoRepository repository;

    private UsuarioRepository repositoryUsuario;

    @Autowired
    public TopicoService(TopicoRepository repository,UsuarioRepository repositoryUsuario) {
        this.repository = repository;
        this.repositoryUsuario = repositoryUsuario;
    }

    /**
     * Insere o tópico no banco de Dados
     * 
     * @param topico
     */

    @Transactional(propagation = Propagation.REQUIRED)
    public Topico inserirTopico(Topico topico) {     

        Topico topicoSalvo = repository.save(topico);
        repositoryUsuario.atualizarPontos(topico.getUsuario(),EPontos.TOPICO.getValor());
        repository.refresh(topicoSalvo);

        return topicoSalvo;
    }

    /**
     * 
     * @param login
     * @return retorna uma lista de tópicos do usuário
     */
    public Page<Topico> getListaTopicos(Usuario usuario, PageRequest page) {
        return repository.findAllTopicosByUsuario(page,usuario);
    }

    /**
     * @return retorna todos os tópicos do fórum
     */
    public Page<Topico> getListaTopicos(PageRequest page) {
        return repository.findAll(page);
    }

    /**
     * @param idTopico
     * @return retorna o tópico dado um ID
     */
    public Topico consultarTopico(Integer id) {
        return repository.getOne(id);
    }

}