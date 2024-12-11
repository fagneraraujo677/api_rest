package br.com.med.voll.clinica.api_rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagem")
public class MensagemController {
    @GetMapping
    public String mensagem() {
        String minhaMensagem = "Olá! Eu de novo por aqui... Agora, já mudei a string.";
        return minhaMensagem;
    }
}
