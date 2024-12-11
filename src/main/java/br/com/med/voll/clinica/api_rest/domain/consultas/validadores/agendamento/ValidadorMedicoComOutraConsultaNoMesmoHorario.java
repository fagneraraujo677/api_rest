package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.ConsultaRepository;
import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosDeAgendamentoDeConsultas dados) {
        var medicoPossueOutraConsultaNoMesmoHorario = repository.existsByMedicoEntityIdAndDataAndMotivoIsNull(dados.idDoMedico(), dados.data());

        if (medicoPossueOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Ops... Médico já possui outra consulta agendada nesse mesmo horário! ");
        }
    }
}
