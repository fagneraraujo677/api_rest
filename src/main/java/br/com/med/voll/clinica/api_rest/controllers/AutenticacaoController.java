package br.com.med.voll.clinica.api_rest.controllers;

import br.com.med.voll.clinica.api_rest.domain.usuarios.DadosDeAutenticacao;
import br.com.med.voll.clinica.api_rest.domain.usuarios.UsuarioEntity;
import br.com.med.voll.clinica.api_rest.domain.usuarios.UsuarioRepository;
import br.com.med.voll.clinica.api_rest.infra.security.DadosDoTokenJWT;
import br.com.med.voll.clinica.api_rest.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(
            @RequestBody
            @Valid
            DadosDeAutenticacao dados,
            UriComponentsBuilder componentsBuilder
    ) {
        var usuario = new UsuarioEntity(dados);
        repository.save(usuario);
        var uri = componentsBuilder.path("/login/cadastrar").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping
    public ResponseEntity efetuarLogin(
            @RequestBody
            @Valid
            DadosDeAutenticacao dados
    ) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((UsuarioEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosDoTokenJWT(tokenJWT));
    }
}
