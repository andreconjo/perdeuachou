package br.com.perdeuachou.api.service;

import br.com.perdeuachou.api.model.pertence.Image;

public interface ImageService {
    Image save(Image image);

    void delete(Image image);
}
