package fr.univrouen.stb23v1.services;

import fr.univrouen.stb23v1.entities.Feature;
import fr.univrouen.stb23v1.repositories.FeatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeatureService {

    private final FeatureRepository featureRepository;
    public Feature save(Feature feature) {
        return featureRepository.save(feature);
    }

}
