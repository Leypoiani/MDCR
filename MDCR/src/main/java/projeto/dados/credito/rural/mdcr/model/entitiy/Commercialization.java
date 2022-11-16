package projeto.dados.credito.rural.mdcr.model.entitiy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Commercialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProduto;
    private String nomeRegiao;
    private String nomeUF;
    @JsonProperty("MesEmissao")
    private String mesEmissao;
    @JsonProperty("AnoEmissao")
    private String anoEmissao;
    private String cdPrograma;
    private String cdSubPrograma;
    private String cdFonteRecurso;
    private String cdTipoSeguro;
    @JsonProperty("QtdCusteio")
    private Integer qtdCusteio;
    @JsonProperty("VlCusteio")
    private BigDecimal vlCusteio;
    @JsonProperty("Atividade")
    private String atividade;
    private String cdModalidade;
    @JsonProperty("AreaCusteio")
    private Double areaCusteio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commercialization that = (Commercialization) o;
        return id.equals(that.id) && nomeProduto.equals(that.nomeProduto) && nomeRegiao.equals(that.nomeRegiao) && nomeUF.equals(that.nomeUF) && mesEmissao.equals(that.mesEmissao) && anoEmissao.equals(that.anoEmissao) && cdPrograma.equals(that.cdPrograma) && cdSubPrograma.equals(that.cdSubPrograma) && cdFonteRecurso.equals(that.cdFonteRecurso) && cdTipoSeguro.equals(that.cdTipoSeguro) && qtdCusteio.equals(that.qtdCusteio) && vlCusteio.equals(that.vlCusteio) && atividade.equals(that.atividade) && cdModalidade.equals(that.cdModalidade) && areaCusteio.equals(that.areaCusteio);
    }

    public Commercialization(Long id, Double areaCusteio, String nomeProduto, String nomeUf, String mesEmissao, String anoEmissao, String cdPrograma, String cdSubPrograma, String cdFonteRecurso, String cdTipoSeguro, Integer qtdCusteio, BigDecimal vlCusteio, String atividade, String cdModalidade) {
    }
}
