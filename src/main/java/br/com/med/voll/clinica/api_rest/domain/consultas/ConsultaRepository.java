package br.com.med.voll.clinica.api_rest.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
    boolean existsByMedicoEntityIdAndDataAndMotivoIsNull(Long idMedico, LocalDateTime data);

    boolean existsByPacienteEntityIdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
