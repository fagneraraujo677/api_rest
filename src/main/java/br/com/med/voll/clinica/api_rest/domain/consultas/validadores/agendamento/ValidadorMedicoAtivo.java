package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.domain.medicos.MedicoRepository;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosDeAgendamentoDeConsultas dados) {
        //escolha do médico opcional.

        if (dados.idDoMedico() == null) {
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idDoMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Ops... Consulta não pode ser agendada com médico inativo! ");
        }
    }
}
