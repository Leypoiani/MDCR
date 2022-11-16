package projeto.dados.credito.rural.mdcr.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.entitiy.CommercializationList;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Service
public class CommercializationServiceTest {

    private static final Long ID = 10L;
    private static final Double AREA_CUSTEIO = null;
    private static final String NOME_PRODUTO = "MILHO";
    private static final String NOME_UF = "CE";
    private static final String MES_EMISSAO = "09";
    private static final String ANO_EMISSAO = "2019";
    private static final String CD_PROGRAMA = "0102";
    private static final String CD_SUB_PROGRAMA = "3";
    private static final String CD_FONTE_RECURSO = "2";
    private static final String CD_TIPO_SEGURO = "3";
    private static final Integer QTD_CUSTEIO = 10;
    private static final BigDecimal VL_CUSTEIO = BigDecimal.valueOf(200000.0);
    private static final String ATIVIDADE = "1";
    private static final String CD_MODALIDADE = null;

    @InjectMocks
    CommercializationService service;
    @Mock
    Commercialization commercialization;
    @Mock
    CommercializationDTO commercializationDTO;
    @Mock
    DataRepository dataRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save")
    public void whenSaveWithSucess(){
        CommercializationDTO dto = new CommercializationDTO();
        dto.setId(123456712L);
        dto.setNomeProduto("teste");
        dto.setNomeRegiao("teste");
        dto.setNomeUF("teste");
        dto.setMesEmissao("teste");
        dto.setAnoEmissao("teste");
        dto.setCdPrograma("teste");
        dto.setCdSubPrograma("teste");
        dto.setCdFonteRecurso("teste");
        dto.setCdTipoSeguro("teste");
        dto.setQtdCusteio(1);
        dto.setVlCusteio(BigDecimal.valueOf(1.0));
        dto.setAtividade("teste");
        dto.setCdModalidade("teste");
        dto.setAreaCusteio(1.0);

        Commercialization c = new Commercialization();
        c.setId(123456712L);
        c.setNomeProduto("teste");
        c.setNomeRegiao("teste");
        c.setNomeUF("teste");
        c.setMesEmissao("teste");
        c.setAnoEmissao("teste");
        c.setCdPrograma("teste");
        c.setCdSubPrograma("teste");
        c.setCdFonteRecurso("teste");
        c.setCdTipoSeguro("teste");
        c.setQtdCusteio(1);
        c.setVlCusteio(BigDecimal.valueOf(1.0));
        c.setAtividade("teste");
        c.setCdModalidade("teste");
        c.setAreaCusteio(1.0);


        when(dataRepository.save(c)).thenReturn(dto);
        CommercializationDTO response = (CommercializationDTO) dataRepository.save(c);

        assertNotNull(response);
        assertEquals(CommercializationDTO.class, response.getClass());
        assertEquals(123456712L, response.getId());

    }

}
