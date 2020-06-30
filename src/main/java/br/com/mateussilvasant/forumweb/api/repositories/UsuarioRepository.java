package br.com.mateussilvasant.forumweb.api.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.mateussilvasant.forumweb.api.configurations.repository.IRepository;
import br.com.mateussilvasant.forumweb.api.model.Usuario;

public interface UsuarioRepository extends IRepository<Usuario,Integer> {
    
    @Transactional
    @Modifying
    @Query(value = "UPDATE Usuario AS u SET u.pontos = u.pontos + :pontosRecebido WHERE u.id = :#{#usuarioLogin.id}")
	public void atualizarPontos(@Param("usuarioLogin") Usuario usuario, @Param("pontosRecebido")int pontos);
    
}