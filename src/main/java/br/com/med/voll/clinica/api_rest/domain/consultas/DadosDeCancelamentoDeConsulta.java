package br.com.med.voll.clinica.api_rest.domain.consultas;

import jakarta.validation.constraints.NotNull;

public record DadosDeCancelamentoDeConsulta(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoDoCancelamento motivo
) {
}
