package br.com.med.voll.clinica.api_rest.domain.consultas;

import br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento.ValidadorAgendamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.domain.consultas.validadores.cancelamento.ValidadorCancelamentoDeConsultas;
import br.com.med.voll.clinica.api_rest.domain.medicos.MedicoEntity;
import br.com.med.voll.clinica.api_rest.domain.medicos.MedicoRepository;
import br.com.med.voll.clinica.api_rest.domain.pacientes.PacienteRepository;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsultas> validadoresCancelamento;

    public DadosDeDetalhamentoDeConsulta agendar(DadosDeAgendamentoDeConsultas dados) {
        if (!pacienteRepository.existsById(dados.idDoPaciente())) {
            throw new ValidacaoException("Ops... Id do paciente informado é inválido! ");
        }

        if (dados.idDoMedico() != null && !medicoRepository.existsById(dados.idDoMedico())) {
            throw new ValidacaoException("Ops... O id do médico informado é inválido! ");
        }

        validadores.forEach(v -> v.validar(dados));



        var paciente = pacienteRepository.getReferenceById(dados.idDoPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Ops... Não existe médico disponível nessa data! ");
        }

        var consulta = new ConsultaEntity(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);

        return new DadosDeDetalhamentoDeConsulta(consulta);
    }

    private MedicoEntity escolherMedico(DadosDeAgendamentoDeConsultas dados) {

        if (dados.idDoMedico() != null) {
            return medicoRepository.getReferenceById(dados.idDoMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Ops... Especialidade é obrigatória quando o médico não é selecionado! ");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosDeCancelamentoDeConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Ops... O id da consulta informado não existe! ");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());

        consulta.cancelar(dados.motivo());
    }
}
