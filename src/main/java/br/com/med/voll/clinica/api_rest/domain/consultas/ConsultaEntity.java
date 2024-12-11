package br.com.med.voll.clinica.api_rest.domain.consultas;

import br.com.med.voll.clinica.api_rest.domain.medicos.MedicoEntity;
import br.com.med.voll.clinica.api_rest.domain.pacientes.PacienteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "ConsultaEntity")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConsultaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private MedicoEntity medicoEntity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private PacienteEntity pacienteEntity;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoDoCancelamento motivo;

    public void cancelar(MotivoDoCancelamento motivo) {
        this.motivo = motivo;
    }
}

