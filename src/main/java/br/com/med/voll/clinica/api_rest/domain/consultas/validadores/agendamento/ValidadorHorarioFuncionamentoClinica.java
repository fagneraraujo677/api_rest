package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {
    public void validar(DadosDeAgendamentoDeConsultas dados) {
        var dataDaConsulta = dados.data();
        var domingo = dataDaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataDaConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataDaConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Ops... Consulta fora do horário de funcionamento da clínica! ");
        }
    }
}
