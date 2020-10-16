package br.com.perdeuachou.api.service.impl;

import br.com.perdeuachou.api.model.Usuario;
import br.com.perdeuachou.api.model.pertence.Pertence;
import br.com.perdeuachou.api.model.pertence.Status;
import br.com.perdeuachou.api.model.pertence.Tipo;
import br.com.perdeuachou.api.repository.PertenceRepository;
import br.com.perdeuachou.api.service.PertenceService;
import br.com.perdeuachou.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PertenceServiceImpl implements PertenceService {

    @Autowired
    PertenceRepository repository;

    @Autowired
    UsuarioService usuarioService;

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
                if (pertence.getData() != null && pertencePerdido.getData().equals(pertence.getData()))
                    score.addAndGet(10);
                pertencePerdido.setScore(score.get());
                if (score.get() > 0)
                    return pertencePerdido;
                return null;
            }).filter(Objects::nonNull).collect(Collectors.toList()));

        });
        if(candidatos.get() != null)
            return candidatos.get().stream().sorted(Comparator.comparing(Pertence::getScore, Comparator.reverseOrder())).collect(Collectors.toList());
        return Collections.emptyList();
    }

    @Override
    public Page<Pertence> getAllByUser(Long userId, String type, Pageable pageable) {
        return repository.findAllByUsuarioIdAndTipo(userId, Tipo.valueOf(type.toUpperCase()), pageable);
    }

    @Override
    public List<Long> cadastrosDaSemana() {
        List<Long> cadastrosNaSemana = new ArrayList<>();

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        Instant instant = c.toInstant();
        LocalDate domingo = instant.atZone(defaultZoneId).toLocalDate();

        Period period = Period.between(domingo, LocalDate.now());

        for(int i = 0; i <= period.getDays(); i++)
            cadastrosNaSemana.add(repository.countAllByDataCadastro(domingo.plusDays(i)));

        return cadastrosNaSemana;
    }

    @Override
    public long cadastros() {
        return repository.count();
    }

    @Override
    public long entregas() {
       return repository.countAllByDataEntregaIsNotNull();
    }

    @Override
    public List<Long> entregasDaSemana() {
        List<Long> entregasDaSemana = new ArrayList<>();

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        Instant instant = c.toInstant();
        LocalDate domingo = instant.atZone(defaultZoneId).toLocalDate();

        Period period = Period.between(domingo, LocalDate.now());

        for(int i = 0; i <= period.getDays(); i++)
            entregasDaSemana.add(repository.countAllByDataEntrega(domingo.plusDays(i)));

        return entregasDaSemana;
    }

    @Override
    public void entrega(Long pertenceId, Long usuarioId) {
        Optional<Pertence> pertence = repository.findById(pertenceId);
        if(pertence.isPresent()) {
            Optional<Usuario> usuario = usuarioService.buscarPorId(usuarioId);
            if(usuario.isPresent()) {
                pertence.get().setDataEntrega(LocalDate.now());
                pertence.get().setEntregue(usuario.get());
                pertence.get().setStatus(Status.DEVOLVIDO);

                repository.save(pertence.get());
            }
        }
    }
}
