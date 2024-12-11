package br.com.med.voll.clinica.api_rest.domain.consultas;

import br.com.med.voll.clinica.api_rest.domain.medicos.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosDeAgendamentoDeConsultas(
        Long idDoMedico,
        @NotNull
        Long idDoPaciente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
) {
}
