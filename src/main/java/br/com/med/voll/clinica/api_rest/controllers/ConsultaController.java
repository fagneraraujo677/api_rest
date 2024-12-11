package br.com.med.voll.clinica.api_rest.controllers;

import br.com.med.voll.clinica.api_rest.domain.consultas.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsultas(
            @RequestBody
            @Valid
            DadosDeAgendamentoDeConsultas dados
    ) {
        var dTo = agenda.agendar(dados);

        return ResponseEntity.ok(dTo);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(
            @RequestBody
            @Valid
            DadosDeCancelamentoDeConsulta dados
    ) {
        agenda.cancelar(dados);

        return ResponseEntity.noContent().build();
    }
}
