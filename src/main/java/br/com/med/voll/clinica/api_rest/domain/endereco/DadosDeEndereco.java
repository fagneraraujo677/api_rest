package br.com.med.voll.clinica.api_rest.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosDeEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade,
        @NotBlank
        String estado,
        @NotBlank  @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String pais,
        @NotBlank
        String numero,
        String complemento
) {
}
