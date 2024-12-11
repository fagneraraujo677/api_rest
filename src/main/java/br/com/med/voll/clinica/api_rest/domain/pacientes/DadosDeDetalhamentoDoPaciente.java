package br.com.med.voll.clinica.api_rest.domain.pacientes;

import br.com.med.voll.clinica.api_rest.domain.endereco.Endereco;

public record DadosDeDetalhamentoDoPaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String celular,
        String corporativo,
        String registro,
        String rg,
        String cpf,
        Endereco endereco
) {

    public DadosDeDetalhamentoDoPaciente(PacienteEntity paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCelular(),
                paciente.getCorporativo(),
                paciente.getRegistro(),
                paciente.getRg(),
                paciente.getCpf(),
                paciente.getEndereco()
        );
    }
}
