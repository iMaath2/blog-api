package br.com.meuprojeto.blog_api.controller;

import br.com.meuprojeto.blog_api.model.Usuario;
import br.com.meuprojeto.blog_api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }
     @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(usuario -> ResponseEntity.ok(usuario)) // Se encontrar, retorna 200 OK com o usuário
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    return ResponseEntity.ok(usuarioRepository.save(usuarioExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!usuarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não existir
        }
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content em caso de sucesso
    }
}
