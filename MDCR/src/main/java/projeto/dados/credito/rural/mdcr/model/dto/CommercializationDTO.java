package projeto.dados.credito.rural.mdcr.model.dto;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;

import java.math.BigDecimal;
@Data
@Getter @Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class CommercializationDTO extends  Commercialization{
    private Long id;
    private String nomeProduto;
    private String nomeRegiao;
    private String nomeUF;
    private String MesEmissao;
    private String AnoEmissao;
    private String cdPrograma;
    private String cdSubPrograma;
    private String cdFonteRecurso;
    private String cdTipoSeguro;
    private Integer QtdCusteio;
    private BigDecimal VlCusteio;
    private String Atividade;
    private String cdModalidade;
    private Double AreaCusteio;

    public CommercializationDTO(Long id, Double areaCusteio, String nomeProduto, String nomeUf, String mesEmissao, String anoEmissao, String cdPrograma, String cdSubPrograma, String cdFonteRecurso, String cdTipoSeguro, Integer qtdCusteio, BigDecimal vlCusteio, String atividade, String cdModalidade) {
    }

    public static CommercializationDTO create1(Commercialization commercialization) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(commercialization, CommercializationDTO.class);
    }
    public static CommercializationDTO create(Commercialization commercialization) {
            CommercializationDTO commercializationDTO = new CommercializationDTO();
            commercializationDTO.setId(commercialization.getId());
            commercializationDTO.setNomeProduto(commercialization.getNomeProduto());
            commercializationDTO.setNomeRegiao(commercialization.getNomeRegiao());
            commercializationDTO.setNomeUF(commercialization.getNomeUF());
            commercializationDTO.setCdPrograma(commercialization.getCdPrograma());
            commercializationDTO.setCdSubPrograma(commercialization.getCdSubPrograma());
            commercializationDTO.setCdFonteRecurso(String.valueOf(commercialization.getCdFonteRecurso()));
            commercializationDTO.setCdTipoSeguro(String.valueOf(commercialization.getCdTipoSeguro()));
            commercializationDTO.setCdModalidade(commercialization.getCdModalidade());
            commercializationDTO.setAnoEmissao(commercialization.getAnoEmissao());
            commercializationDTO.setMesEmissao(commercialization.getMesEmissao());
            commercializationDTO.setAtividade(commercialization.getAtividade());
            commercializationDTO.setVlCusteio(commercialization.getVlCusteio());
            commercializationDTO.setAreaCusteio(commercialization.getAreaCusteio());
            commercializationDTO.setQtdCusteio(commercialization.getQtdCusteio());
            return commercializationDTO;

    }

}
