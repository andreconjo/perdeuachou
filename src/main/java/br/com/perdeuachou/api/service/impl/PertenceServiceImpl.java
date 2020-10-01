package br.com.perdeuachou.api.service.impl;

import br.com.perdeuachou.api.model.pertence.Pertence;
import br.com.perdeuachou.api.model.pertence.Tipo;
import br.com.perdeuachou.api.repository.PertenceRepository;
import br.com.perdeuachou.api.service.PertenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PertenceServiceImpl implements PertenceService {

    @Autowired
    PertenceRepository repository;

    @Override
    public Pertence save(Pertence pertence){
        return repository.save(pertence);
    }

    @Override
    public Page<Pertence> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Pertence> getMatches(Long userId, Pageable pageable) {
        List<Pertence> todosAchados = repository.findAllByTipoAndUsuarioId(Tipo.ACHADO, userId);
        List<Pertence> todosPerdidos = repository.findAllByTipo(Tipo.PERDIDO);

        AtomicReference<List<Pertence>> candidatos = new AtomicReference<>();

        todosAchados
                .stream().parallel().forEach(pertence -> {
            candidatos.set(todosPerdidos.stream().map(pertencePerdido -> {
                AtomicInteger score = new AtomicInteger();
                if (pertencePerdido.getCategoria().toLowerCase().contains(pertence.getCategoria().toLowerCase()))
                    score.addAndGet(20);
                if (pertencePerdido.getDescricao().toLowerCase().contains(pertence.getDescricao().toLowerCase()))
                    score.addAndGet(10);
                if (pertencePerdido.getPerdidoEm().toLowerCase().contains(pertence.getPerdidoEm().toLowerCase()))
                    score.addAndGet(10);
                if (pertencePerdido.getData().equals(pertence.getData()))
                    score.addAndGet(10);
                pertencePerdido.setScore(score.get());
                if (score.get() > 0)
                    return pertencePerdido;
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList()));

        });

        return candidatos.get().stream().sorted(Comparator.comparing(Pertence::getScore, Comparator.reverseOrder())).collect(Collectors.toList());
    }


}
