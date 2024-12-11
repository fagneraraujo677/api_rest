package br.com.med.voll.clinica.api_rest.domain.pacientes;

import br.com.med.voll.clinica.api_rest.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "PacienteEntity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean ativo;
    private String nome;
    private String email;
    private String telefone;
    private String celular;
    private String corporativo;
    private String registro;
    private String rg;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public PacienteEntity(DadosDeCadastroDoPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.celular = dados.celular();
        this.corporativo = dados.corporativo();
        this.registro = dados.registro();
        this.rg = dados.rg();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarCadastro(DadosDeAtualizacaoDePaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.celular() != null) {
            this.celular = dados.celular();
        }
        if (dados.rg() != null) {
            this.rg = dados.rg();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void inativarCadastro() {
        this.ativo = false;
    }
}
