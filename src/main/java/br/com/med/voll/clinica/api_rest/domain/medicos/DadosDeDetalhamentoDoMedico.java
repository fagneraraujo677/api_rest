package br.com.med.voll.clinica.api_rest.domain.medicos;

import br.com.med.voll.clinica.api_rest.domain.endereco.Endereco;

public record DadosDeDetalhamentoDoMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String celular,
        String corporativo,
        String crm,
        Especialidade especialidade,
        String rg,
        String cpf,
        Endereco endereco

) {
    public DadosDeDetalhamentoDoMedico(MedicoEntity medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCelular(),
                medico.getCorporativo(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getRg(),
                medico.getCpf(),
                medico.getEndereco()
        );
    }
}
