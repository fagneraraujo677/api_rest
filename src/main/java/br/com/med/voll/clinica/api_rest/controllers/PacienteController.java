package br.com.med.voll.clinica.api_rest.controllers;

import br.com.med.voll.clinica.api_rest.domain.pacientes.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody
            @Valid
            DadosDeCadastroDoPaciente dados,
            UriComponentsBuilder componentsBuilder
    ) {

        var paciente = new PacienteEntity(dados);

        repository.save(paciente);

        var uri = componentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDeDetalhamentoDoPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDeListagemDePaciente>> listarPacientes(
            @PageableDefault(size = 10, sort = {"nome"})
            Pageable paginacao
    ) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosDeListagemDePaciente::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPaciente(
            @RequestBody
            @Valid
            DadosDeAtualizacaoDePaciente dados
    ) {

        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarCadastro(dados);

        return ResponseEntity.ok(new DadosDeDetalhamentoDoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativarPaciente(
            @PathVariable
            Long id
    ) {
        var paciente = repository.getReferenceById(id);
        paciente.inativarCadastro();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(
            @PathVariable
            Long id
    ) {
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDeDetalhamentoDoPaciente(paciente));
    }

}