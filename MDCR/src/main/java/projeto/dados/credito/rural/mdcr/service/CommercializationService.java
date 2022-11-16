package projeto.dados.credito.rural.mdcr.service;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CommercializationService {
    @Autowired
    DataRepository dataRepository;
    public CommercializationDTO save(Commercialization commercialization){
        Assert.isNull(commercialization.getId(), "Não foi possível inserir no banco.");
        return CommercializationDTO.create(dataRepository.save(commercialization));
    }

}
