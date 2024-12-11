package br.com.med.voll.clinica.api_rest.domain.consultas;

import java.time.LocalDateTime;

public record DadosDeDetalhamentoDeConsulta(
        Long id,
        Long idDoMedico,
        Long idDoPaciente,
        LocalDateTime data
) {
    public DadosDeDetalhamentoDeConsulta(ConsultaEntity consulta) {
        this(
                consulta.getId(),
                consulta.getMedicoEntity().getId(),
                consulta.getPacienteEntity().getId(),
                consulta.getData()
        );
    }
}
