package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas {
    public void validar(DadosDeAgendamentoDeConsultas dados) {
        var dataDaConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataDaConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Ops... Consulta deve ser agendada com antecedência mínima de 30 minutos! ");
        }
    }
}
