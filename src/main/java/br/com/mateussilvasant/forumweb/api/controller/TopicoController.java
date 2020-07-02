package br.com.mateussilvasant.forumweb.api.controller;

import java.util.function.Function;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateussilvasant.forumweb.api.dtos.response.TopicoCadastro;
import br.com.mateussilvasant.forumweb.api.dtos.response.TopicoResumo;
import br.com.mateussilvasant.forumweb.api.model.Topico;
import br.com.mateussilvasant.forumweb.api.model.Usuario;
import br.com.mateussilvasant.forumweb.api.services.ComentarioService;
import br.com.mateussilvasant.forumweb.api.services.TopicoService;
import br.com.mateussilvasant.forumweb.api.services.UsuarioService;
import br.com.mateussilvasant.forumweb.api.utils.DTOConverter;

@RestController
@RequestMapping("api")
public class TopicoController {

    @Autowired
    private TopicoService service;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/topico")
    public ResponseEntity<TopicoCadastro> inserirTopico(@Valid @RequestBody Topico topico) {

        Topico topicoSalvo = service.inserirTopico(topico);

        TopicoCadastro dto = new DTOConverter<TopicoCadastro, Topico>(Topico.class, TopicoCadastro.class)
                .converterToDTO(topicoSalvo, null);

        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/topico/{id}")
    public ResponseEntity<Topico> consultarTopico(@PathVariable("id") Integer id) {
        Topico topicoSalvo = service.consultarTopico(id);
        return ResponseEntity.ok(topicoSalvo);
    }

    @GetMapping(value = "/topicos", params = { "page", "size" })
    public Page<TopicoResumo> listarTopicos(@RequestParam("page") @Min(0) int page,
            @RequestParam("size") @Min(5) int size) {

        PageRequest pageRequest = PageRequest.of(page, size, Direction.ASC, "dataCriacao");

        Page<Topico> pages = service.getListaTopicos(pageRequest);

        Page<TopicoResumo> dtos = converterPagesDTO(pages);

        return dtos;
    }

    @GetMapping(value = "/topicos", params = { "page", "size", "usuario" })
    public Page<TopicoResumo> listarTopicos(@RequestParam("page") @Min(0) int page,
            @RequestParam("size") @Min(5) int size, @RequestParam("usuario") @Valid Integer idUsuario) {

        PageRequest pageRequest = PageRequest.of(page, size, Direction.ASC, "dataCriacao");

        Usuario usuario = usuarioService.consultarUsuario(idUsuario);

        Page<Topico> pages = service.getListaTopicos(usuario, pageRequest);

        Page<TopicoResumo> dtos = converterPagesDTO(pages);

        return dtos;

    }

    private Page<TopicoResumo> converterPagesDTO(Page<Topico> pages) {
        return new DTOConverter<TopicoResumo, Topico>(Topico.class, TopicoResumo.class).converterAllToDTO(pages,
                adicionarQTDComentario(), new PropertyMap<Topico, TopicoResumo>() {

                    @Override
                    protected void configure() {
                        map().setConteudo(source.getConteudoResumo());
                        map().setNomeCriador(source.getUsuario().getNome());
                    }

                });
    }

    private Function<TopicoResumo, TopicoResumo> adicionarQTDComentario() {
        return (topicoResumo) -> {

            Topico topico = new Topico();
            topico.setId(topicoResumo.getId());

            int qtd = (int) comentarioService.getQuantidadeComentarios(topico);

            topicoResumo.setQuantidadeComentarios(qtd);

            return topicoResumo;
        };
    }

}