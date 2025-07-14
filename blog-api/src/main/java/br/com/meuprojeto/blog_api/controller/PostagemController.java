package br.com.meuprojeto.blog_api.controller;

import br.com.meuprojeto.blog_api.dto.PostagemRequestDTO;
import br.com.meuprojeto.blog_api.model.Postagem;
import br.com.meuprojeto.blog_api.service.PostagemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostagemController {
    @Autowired private PostagemService postagemService;

    @PostMapping("/usuarios/{usuarioId}/postagens")
    public ResponseEntity<Postagem> criarPostagem(@PathVariable Long usuarioId, @Valid @RequestBody PostagemRequestDTO postagemDTO) {
        return postagemService.criarPostagem(usuarioId, postagemDTO)
                .map(postagem -> ResponseEntity.status(HttpStatus.CREATED).body(postagem))
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/usuarios/{usuarioId}/postagens")
    public ResponseEntity<List<Postagem>> buscarPostagensPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postagemService.buscarPostagensPorUsuario(usuarioId));
    }
        @GetMapping("/postagens/{id}")
    public ResponseEntity<Postagem> buscarPostagemPorId(@PathVariable Long id) {
        return postagemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ATUALIZAR uma postagem
    @PutMapping("/postagens/{id}")
    public ResponseEntity<Postagem> atualizarPostagem(@PathVariable Long id, @Valid @RequestBody PostagemRequestDTO postagemDTO) {
        return postagemService.atualizarPostagem(id, postagemDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETAR uma postagem
    @DeleteMapping("/postagens/{id}")
    public ResponseEntity<Void> removerPostagem(@PathVariable Long id) {
        if (postagemService.removerPostagem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

