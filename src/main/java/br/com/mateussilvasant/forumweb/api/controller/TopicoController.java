package br.com.mateussilvasant.forumweb.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateussilvasant.forumweb.api.dtos.TopicoDTO;
import br.com.mateussilvasant.forumweb.api.model.Topico;
import br.com.mateussilvasant.forumweb.api.services.TopicoService;
import br.com.mateussilvasant.forumweb.api.utils.DTOConverter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("api")
public class TopicoController {

    @Autowired
    private TopicoService service;
    
    @PostMapping("/topico")
    public ResponseEntity<TopicoDTO> inserirTopico(@Valid @RequestBody Topico topico){

        Topico topicoSalvo = service.inserirTopico(topico);

        

        TopicoDTO dto = new DTOConverter<TopicoDTO,Topico>()
            .converterToDTO(topicoSalvo, Topico.class, TopicoDTO.class, null);

        dto.add(linkTo(TopicoController.class).slash(dto.getId()).withSelfRel());

        return ResponseEntity.ok(dto);
    }
        
    

}