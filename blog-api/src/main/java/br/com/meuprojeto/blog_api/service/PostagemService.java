package br.com.meuprojeto.blog_api.service;

import br.com.meuprojeto.blog_api.dto.PostagemRequestDTO;
import br.com.meuprojeto.blog_api.model.Postagem;
import br.com.meuprojeto.blog_api.model.Usuario;
import br.com.meuprojeto.blog_api.repository.PostagemRepository;
import br.com.meuprojeto.blog_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostagemService {

    @Autowired private PostagemRepository postagemRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    @Transactional
    public Optional<Postagem> criarPostagem(Long usuarioId, PostagemRequestDTO postagemDTO) {
        return usuarioRepository.findById(usuarioId).map(usuario -> {
            Postagem novaPostagem = new Postagem();
            novaPostagem.setTitulo(postagemDTO.getTitulo());
            novaPostagem.setConteudo(postagemDTO.getConteudo());
            novaPostagem.setUsuario(usuario);
            return postagemRepository.save(novaPostagem);
        });
    }

    public List<Postagem> buscarPostagensPorUsuario(Long usuarioId) {
        return postagemRepository.findByUsuarioId(usuarioId);
    }
        public List<Postagem> buscarTodas() {
        return postagemRepository.findAll();
    }

    public Optional<Postagem> buscarPorId(Long id) {
        return postagemRepository.findById(id);
    }

    @Transactional
    public Optional<Postagem> atualizarPostagem(Long id, PostagemRequestDTO postagemDTO) {
        return postagemRepository.findById(id)
                .map(postagemExistente -> {
                    postagemExistente.setTitulo(postagemDTO.getTitulo());
                    postagemExistente.setConteudo(postagemDTO.getConteudo());
                    return postagemRepository.save(postagemExistente);
                });
    }

    public boolean removerPostagem(Long id) {
        if (postagemRepository.existsById(id)) {
            postagemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}