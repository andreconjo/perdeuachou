package br.com.perdeuachou.api.repository;

import br.com.perdeuachou.api.model.pertence.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
