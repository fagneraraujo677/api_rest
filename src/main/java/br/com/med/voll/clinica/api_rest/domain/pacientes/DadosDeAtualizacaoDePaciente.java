package br.com.med.voll.clinica.api_rest.domain.pacientes;

import br.com.med.voll.clinica.api_rest.domain.endereco.DadosDeEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosDeAtualizacaoDePaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String celular,
        String rg,
        DadosDeEndereco endereco
) {
}
