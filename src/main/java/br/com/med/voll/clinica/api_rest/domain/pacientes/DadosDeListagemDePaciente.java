package br.com.med.voll.clinica.api_rest.domain.pacientes;

public record DadosDeListagemDePaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String corporativo,
        String registro,
        String rg
) {
    public DadosDeListagemDePaciente(PacienteEntity paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getTelefone(),
                paciente.getCorporativo(),
                paciente.getRegistro(),
                paciente.getRg()
        );
    }
}

