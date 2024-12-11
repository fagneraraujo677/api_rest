package br.com.med.voll.clinica.api_rest.domain.medicos;

import br.com.med.voll.clinica.api_rest.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "MedicoEntity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String celular;
    private String corporativo;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private String rg;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public MedicoEntity(DadosDeCadastroDeMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.celular = dados.celular();
        this.corporativo = dados.corporativo();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.rg = dados.rg();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarCadastro(DadosDeAtualizacaoDoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.corporativo() != null) {
            this.corporativo = dados.corporativo();
        }
        if (dados.rg() != null) {
            this.rg = dados.rg();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void inativarMedico() {
        this.ativo = false;
    }
}


