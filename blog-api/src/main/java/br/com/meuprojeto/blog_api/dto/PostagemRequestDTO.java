package br.com.meuprojeto.blog_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PostagemRequestDTO {
    @NotBlank(message = "O título não pode estar em branco.")
    @Size(min = 5, message = "O título deve ter no mínimo 5 caracteres.")
    private String titulo;

    @NotBlank(message = "O conteúdo não pode estar em branco.")
    private String conteudo;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
}
