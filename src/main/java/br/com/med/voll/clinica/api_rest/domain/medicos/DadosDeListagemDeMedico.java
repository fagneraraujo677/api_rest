package br.com.med.voll.clinica.api_rest.domain.medicos;

public record DadosDeListagemDeMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String celular,
        String corporativo,
        String crm,
        Especialidade especialidade,
        String rg
) {
    public DadosDeListagemDeMedico(MedicoEntity medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEmail(),
                medico.getTelefone(),
                medico.getCelular(),
                medico.getCorporativo(),
                medico.getCrm(),
                medico.getEspecialidade(),
                medico.getRg()
        );
    }
}
