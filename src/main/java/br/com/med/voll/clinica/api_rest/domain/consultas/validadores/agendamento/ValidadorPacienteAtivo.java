package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.domain.pacientes.PacienteRepository;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {
    @Autowired
    private PacienteRepository repository;
    public void validar(DadosDeAgendamentoDeConsultas dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idDoPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Ops... Consulta só pode ser agendada com paciente ativo no sistema! ");
        }
    }
}
