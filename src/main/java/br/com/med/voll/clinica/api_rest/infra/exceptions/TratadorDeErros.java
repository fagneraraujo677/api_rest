package br.com.med.voll.clinica.api_rest.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException validException) {

        var erros = validException.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosDeErrosDeValidacao::new).toList());
    }

    private record DadosDeErrosDeValidacao(
            String campo,
            String mensagem
    ) {

        public DadosDeErrosDeValidacao(FieldError error) {
            this(
                    error.getField(),
                    error.getDefaultMessage()
            );
        }
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
