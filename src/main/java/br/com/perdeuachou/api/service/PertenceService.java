package br.com.perdeuachou.api.service;

import br.com.perdeuachou.api.model.pertence.Pertence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PertenceService {
    Pertence save(Pertence pertence);

    Page<Pertence> getAllPaginated(Pageable pageable);
}
