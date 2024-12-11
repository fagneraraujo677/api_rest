package br.com.med.voll.clinica.api_rest.controllers;

import br.com.med.voll.clinica.api_rest.domain.medicos.*;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody
            @Valid
            DadosDeCadastroDeMedico dados,
            UriComponentsBuilder componentsBuilder
    ) {
        var medico = new MedicoEntity(dados);
        repository.save(medico);
        var uri = componentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDeDetalhamentoDoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDeListagemDeMedico>> listarMedicos(
            @PageableDefault(size = 10, sort = {"nome"}, page = 0)
            Pageable paginacao
    ) {

        var page = repository.findAllByAtivoTrue(paginacao).map(DadosDeListagemDeMedico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(
            @RequestBody
            @Valid
            DadosDeAtualizacaoDoMedico dados
    ) {

        var medico = repository.getReferenceById(dados.id());
        medico.atualizarCadastro(dados);

        return ResponseEntity.ok(new DadosDeDetalhamentoDoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativarCadastro(
            @PathVariable
            Long id
    ) {
        var medico = repository.getReferenceById(id);
        medico.inativarMedico();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(
            @PathVariable
            Long id
    ) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDeDetalhamentoDoMedico(medico));
    }
}
