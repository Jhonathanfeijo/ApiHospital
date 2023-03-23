package br.com.hospital.model.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank String login,@NotBlank String password) {

}
