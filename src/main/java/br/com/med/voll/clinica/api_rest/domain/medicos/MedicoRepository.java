package br.com.med.voll.clinica.api_rest.domain.medicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
    Page<MedicoEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
            select m from MedicoEntity m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medicoEntity.id from ConsultaEntity c
                where
                c.data = :data
                and
                c.motivo is null
                
            )
            order by rand()
            limit 1
            """)
    MedicoEntity escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

    @Query("""
            select m.ativo from MedicoEntity m
            where
            m.id = :id
            
            """)
    Boolean findAtivoById(Long id);
}

