package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.cancelamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeCancelamentoDeConsulta;

public interface ValidadorCancelamentoDeConsultas {

    void validar(DadosDeCancelamentoDeConsulta dados);
}
