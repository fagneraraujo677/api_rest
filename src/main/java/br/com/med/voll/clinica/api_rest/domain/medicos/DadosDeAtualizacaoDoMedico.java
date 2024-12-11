package br.com.med.voll.clinica.api_rest.domain.medicos;

import br.com.med.voll.clinica.api_rest.domain.endereco.DadosDeEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosDeAtualizacaoDoMedico(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        String corporativo,
        String rg,
        DadosDeEndereco endereco
) {
}
