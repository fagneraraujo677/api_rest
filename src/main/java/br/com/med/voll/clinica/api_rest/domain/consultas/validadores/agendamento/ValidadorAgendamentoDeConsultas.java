package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.agendamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeAgendamentoDeConsultas;

public interface ValidadorAgendamentoDeConsultas {
    void validar(DadosDeAgendamentoDeConsultas dados);
}
