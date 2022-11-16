package projeto.dados.credito.rural.mdcr.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private CommercializationDTO commercializationDTO;
    public List<CommercializationDTO> getAll(){
        return dataRepository.findAll().stream().map(CommercializationDTO::create).collect(Collectors.toList());
    }
    public CommercializationDTO getById(Long id) {
        return dataRepository.findById(id).map(CommercializationDTO::create).orElseThrow(() -> new ObjectNotFoundException(0,"ID não encontrado"));
    }
    public Commercialization insert(Commercialization commercialization) {
        Commercialization c = dataRepository.save(commercialization);
        if (c.getNomeProduto() != null){
            return c;
        }else throw new DataIntegrityViolationException("Não foi possível criar o registro.");


    }
    public CommercializationDTO update(CommercializationDTO commercializationDTO, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro.");
        Optional<Commercialization> optional = dataRepository.findById(id);
        if (optional.isPresent()) {
            Commercialization db = optional.get();

            db.setNomeProduto(commercializationDTO.getNomeProduto());
            db.setNomeRegiao(commercializationDTO.getNomeRegiao());
            db.setNomeUF(commercializationDTO.getNomeUF());
            db.setCdPrograma(commercializationDTO.getCdPrograma());
            db.setCdSubPrograma(commercializationDTO.getCdSubPrograma());
            db.setCdFonteRecurso(String.valueOf(commercializationDTO.getCdFonteRecurso()));
            db.setCdTipoSeguro(String.valueOf(commercializationDTO.getCdTipoSeguro()));
            db.setCdModalidade(commercializationDTO.getCdModalidade());
            db.setAnoEmissao(commercializationDTO.getAnoEmissao());
            db.setMesEmissao(commercializationDTO.getMesEmissao());
            db.setAtividade(commercializationDTO.getAtividade());
            db.setVlCusteio(commercializationDTO.getVlCusteio());
            db.setAreaCusteio(commercializationDTO.getAreaCusteio());
            db.setQtdCusteio(commercializationDTO.getQtdCusteio());
            System.out.println("Registro id " + db.getId());

            return CommercializationDTO.create(dataRepository.save(db));

        } else {
            throw new DataIntegrityViolationException("Não foi possível atualizar o registro.");
        }
    }
    public boolean delete(Long id) {
        CommercializationDTO commercialization = getById(id);
        if(commercialization != null ){
            dataRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
