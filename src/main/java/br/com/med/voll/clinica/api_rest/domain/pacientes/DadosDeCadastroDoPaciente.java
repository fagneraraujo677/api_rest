package br.com.med.voll.clinica.api_rest.domain.pacientes;

import br.com.med.voll.clinica.api_rest.domain.endereco.DadosDeEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record DadosDeCadastroDoPaciente(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotBlank @Pattern(regexp = "\\(?\\d{2}\\)?\\d{4}\\-?\\d{4}")
        String telefone,
        @NotBlank   @Pattern(regexp = "\\(?\\d{2}\\)?\\d{5}\\-?\\d{4}")
        String celular,
        @NotBlank   @Pattern(regexp = "\\(?\\d{2}\\)?\\d{5}\\-?\\d{4}")
        String corporativo,
        @NotBlank  @Pattern(regexp = "\\d{4,6}")
        String registro,
        @NotBlank   @Pattern(regexp = "\\d{2}\\.?\\d{3}\\.?\\d{3}\\-?\\d{1}")
        String rg,
        @NotBlank  @CPF
        String cpf,
        @NotNull   @Valid
        DadosDeEndereco endereco

) {
}
