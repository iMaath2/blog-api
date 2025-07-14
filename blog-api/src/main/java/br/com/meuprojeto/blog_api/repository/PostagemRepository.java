package br.com.meuprojeto.blog_api.repository;

import br.com.meuprojeto.blog_api.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    List<Postagem> findByUsuarioId(Long usuarioId);
}