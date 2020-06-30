package br.com.mateussilvasant.forumweb.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateussilvasant.forumweb.api.model.Usuario;
import br.com.mateussilvasant.forumweb.api.services.UsuarioService;

@RestController
@RequestMapping("api")
public class UsuarioController {
    

    @Autowired
    private UsuarioService service;
    
    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity<Usuario> consultarUsuario(@PathVariable("id") Integer id){

        Usuario usuario = service.consultarUsuario(id);

        return ResponseEntity.ok(usuario);
    }
}