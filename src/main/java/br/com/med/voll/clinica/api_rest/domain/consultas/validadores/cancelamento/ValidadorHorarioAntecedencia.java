package br.com.med.voll.clinica.api_rest.domain.consultas.validadores.cancelamento;

import br.com.med.voll.clinica.api_rest.domain.consultas.ConsultaRepository;
import br.com.med.voll.clinica.api_rest.domain.consultas.DadosDeCancelamentoDeConsulta;
import br.com.med.voll.clinica.api_rest.infra.exceptions.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosDeCancelamentoDeConsulta dados) {

        var consulta = repository.getReferenceById(dados.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Ops... O cancelamento da consulta só poderá ser realizada no período mínimo de 24h! ");
        }
    }
}
