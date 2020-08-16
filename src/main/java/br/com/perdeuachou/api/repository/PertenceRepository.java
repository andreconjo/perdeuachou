package br.com.perdeuachou.api.repository;

import br.com.perdeuachou.api.model.pertence.Pertence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PertenceRepository extends JpaRepository<Pertence, Long> {
}
