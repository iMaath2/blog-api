package br.com.meuprojeto.blog_api.repository;

import br.com.meuprojeto.blog_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}