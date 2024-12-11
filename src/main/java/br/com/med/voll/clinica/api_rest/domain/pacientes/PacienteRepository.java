package br.com.med.voll.clinica.api_rest.domain.pacientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
    Page<PacienteEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select p.ativo from PacienteEntity p
            where
            p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
