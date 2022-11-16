package projeto.dados.credito.rural.mdcr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class FilterService {
    @Autowired
    private DataRepository dataRepository;
    @Autowired
    private DataService dataService;

    public List<CommercializationDTO> getAllPageable(Pageable pageable) {
        return dataRepository.findAll(pageable).stream()
                .map(CommercializationDTO::create).collect(Collectors.toList());
    }

    public List<String> findByYear(String anoEmissao) {
        List<String> x = dataRepository.findByYear(anoEmissao);
        if(x != null){
            return x;
        }else throw new RuntimeException("Não encontrado.");
    }
    public List<Commercialization> findByKeyWord(String keyWord) {
        List<Commercialization> x = dataRepository.search(keyWord);
        if(x != null){
            return x;
        }else throw new RuntimeException("Não encontrado.");
    }

}
