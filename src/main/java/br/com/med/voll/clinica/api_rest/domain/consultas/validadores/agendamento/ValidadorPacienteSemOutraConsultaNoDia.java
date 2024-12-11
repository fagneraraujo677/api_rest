package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.ConsultaRepository;
import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosDeAgendamentoDeConsultas dados) {

        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossueOutraConsultaNoMesmoDia = repository.existsByPacienteEntityIdAndDataBetween(dados.idDoPaciente(), primeiroHorario, ultimoHorario);

        if (pacientePossueOutraConsultaNoMesmoDia) {
            throw new ValidacaoException("Ops... Paciente já possui uma consulta agendada nesse dia! ");
        }
    }
}
