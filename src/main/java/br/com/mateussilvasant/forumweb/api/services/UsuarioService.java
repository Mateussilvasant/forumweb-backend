package br.com.mateussilvasant.forumweb.api.services;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mateussilvasant.forumweb.api.model.Usuario;
import br.com.mateussilvasant.forumweb.api.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    /**
     * Realiza cadastro do usuario no banco de dados
     * 
     * @param usuario
     */

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {

        ExampleMatcher matcher =  ExampleMatcher.matching();
        GenericPropertyMatcher gen = new GenericPropertyMatcher();

        matcher.withMatcher("login", gen.exact());
        matcher.withMatcher("email", gen.exact());

        if(!repository.exists(Example.of(usuario, matcher))){
            Usuario usuarioSalvo = repository.save(usuario);
            repository.refresh(usuarioSalvo);
            return usuarioSalvo;
        } else {
            throw new EntityExistsException();
        }

    }


    /**
     * @return retorna a lista de ranking de usuarios.
     */
    public List<Usuario> listarRanking()  {
        return repository.findAll(Sort.by(Direction.ASC, "pontos"));
    }

}