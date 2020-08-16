package br.com.perdeuachou.api.service.impl;

import br.com.perdeuachou.api.model.pertence.Image;
import br.com.perdeuachou.api.repository.ImageRepository;
import br.com.perdeuachou.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository repository;

    @Override
    public Image save(Image image) {
        return this.repository.save(image);
    }

    @Override
    public void delete(Image image) {
        this.repository.delete(image);
    }


}
