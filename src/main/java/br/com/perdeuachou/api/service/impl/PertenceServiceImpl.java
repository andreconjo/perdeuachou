package br.com.perdeuachou.api.service.impl;

import br.com.perdeuachou.api.model.pertence.Pertence;
import br.com.perdeuachou.api.repository.PertenceRepository;
import br.com.perdeuachou.api.service.PertenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
