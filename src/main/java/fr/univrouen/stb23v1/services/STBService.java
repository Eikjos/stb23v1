package fr.univrouen.stb23v1.services;

import fr.univrouen.stb23v1.entities.STB;
import fr.univrouen.stb23v1.model.*;
import fr.univrouen.stb23v1.repositories.StbRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class STBService {

    private final StbRepository stbRepository;
    private final ModelMapper mapper = new ModelMapper();

    public STBListModel findAll() {
        List<STB> all = stbRepository.findAll();
        var result = new ArrayList<STBModel>();
        for (STB elem: all) {
            var v = mapper.map(elem, STBModel.class);
            v.setClient(elem.getClient().getEntity());
            result.add(v);
        }
        return new STBListModel(result);
    }

    public Optional<STB> findByIdEntity(Long id) {
        return stbRepository.findById(id);
    }

    public DetailSTBModel findById(Long id) {
        var stb = stbRepository.findById(id);
        if (stb.isEmpty()) {
            return null;
        }
        var v = mapper.map(stb.get(), DetailSTBModel.class);
        var features = stb.get().getFeatures();
        v.setFeature(features.stream().map(FeatureModel::new).toList());
        v.setMember(stb.get().getTeam().stream().map(MemberModel::new).toList());
        return v;
    }

    public boolean isPresent(String title, Date date, double version) {
        return stbRepository.findByTitleAndAndDateAndVersion(title, date, version)
                .isPresent();
    }

    public STB save(STB stb) {
        return stbRepository.save(stb);
    }

    public void delete(STB stb) {
        stbRepository.delete(stb);
    }
}
