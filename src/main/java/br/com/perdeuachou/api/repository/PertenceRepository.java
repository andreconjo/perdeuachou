package br.com.perdeuachou.api.repository;

import br.com.perdeuachou.api.model.pertence.Pertence;
import br.com.perdeuachou.api.model.pertence.Tipo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PertenceRepository extends JpaRepository<Pertence, Long> {

    List<Pertence> findAllByTipoAndUsuarioId(Tipo tipo, Long userId);
    List<Pertence> findAllByTipo(Tipo tipo);

    Long countAllByDataCadastro(LocalDate data);
    Long countAllByDataEntrega(LocalDate data);

    Page<Pertence> findAllByUsuarioIdAndTipo(Long userId, Tipo tipo, Pageable pageable);

}
