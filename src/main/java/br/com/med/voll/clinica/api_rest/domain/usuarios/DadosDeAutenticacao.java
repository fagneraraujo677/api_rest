package br.com.med.voll.clinica.api_rest.domain.usuarios;

public record DadosDeAutenticacao(
        String login,
        String senha
) {
}
