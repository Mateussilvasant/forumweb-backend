package br.com.mateussilvasant.forumweb.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateussilvasant.forumweb.api.model.Comentario;
import br.com.mateussilvasant.forumweb.api.model.Topico;
import br.com.mateussilvasant.forumweb.api.services.ComentarioService;
import br.com.mateussilvasant.forumweb.api.services.TopicoService;

@RestController
@RequestMapping("api")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @Autowired
    private TopicoService topicoService;


    @PostMapping(value = "/comentario")
    public ResponseEntity<Comentario> inserirComentario(@RequestBody @Valid Comentario comentario) {

        Comentario comentarioSalvo = service.cadastrarComentario(comentario);

        return ResponseEntity.ok(comentarioSalvo);
    }

    @GetMapping(value = "/comentarios")
    public List<Comentario> listarComentarios(@RequestParam("topico") Integer idTopico){

        Topico topico = topicoService.consultarTopico(idTopico);

        List<Comentario> comentarios = service.getListaComentarios(topico);

        return comentarios;
    }
    
}