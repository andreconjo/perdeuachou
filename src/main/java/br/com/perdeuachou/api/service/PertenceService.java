package br.com.perdeuachou.api.service;

import br.com.perdeuachou.api.model.pertence.Pertence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PertenceService {
    Pertence save(Pertence pertence);

    Page<Pertence> getAllPaginated(Pageable pageable);

    List<Pertence> getMatches(Long userId, Pageable pageable);

    Page<Pertence> getAllByUser(Long userId, String type, Pageable pageable);

    List<Long> cadastrosDaSemana();

    List<Long> entregasDaSemana();

    void entrega(Long pertenceId, Long usuarioId);
}
