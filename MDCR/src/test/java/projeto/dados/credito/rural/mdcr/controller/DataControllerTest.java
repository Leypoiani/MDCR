package projeto.dados.credito.rural.mdcr.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;

import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.service.DataService;
import projeto.dados.credito.rural.mdcr.service.FilterService;

import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DataControllerTest {

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

    @Mock
    CommercializationDTO commercializationDTO;
    @Mock
    Commercialization commercialization;
    @InjectMocks
    DataController controller;
    @Mock
    private DataService dataService;

    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startCommercializations();
    }

    @Test
    @DisplayName("getByIdSucess")
    void whenFindByIdThenReturnSucess(){

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

        when(dataService.getById(dto.getId())).thenReturn(dto);

        ResponseEntity<CommercializationDTO> response = controller.getById(dto.getId());

        assertNotNull(response);
        assertThat(response.getStatusCode(),is(HttpStatus.OK));
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(response.getBody().getId(), dto.getId());

    }

    @Test
    @DisplayName("getAll")
    void whenFindAllThenReturnAnListOfCommercializationDTO() {

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

        when(dataService.getAll()).thenReturn(List.of(dto));

        ResponseEntity<List<CommercializationDTO>> response = controller.getAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CommercializationDTO.class, response.getBody().get(0).getClass());
        assertEquals(dto.getId(), response.getBody().get(0).getId());
    }
    @Test
    @DisplayName("insert")
    void whenInsertThenReturnCreated() {
        when(dataService.insert(any())).thenReturn(commercializationDTO);

        ResponseEntity response = controller.post(commercializationDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertNotNull(response.getHeaders().get("Location"));
    }


    @Test
    @DisplayName("update")
    void whenUpdateThenReturnSucess() {

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

        when(dataService.update(dto, dto.getId())).thenReturn(dto);

        ResponseEntity<CommercializationDTO> response = controller.update(dto.getId(), dto);

        assertNotNull(response);
        assertEquals(response.getBody().getId(), dto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CommercializationDTO.class, response.getBody().getClass());
    }
    @Test
    @DisplayName("delete")
    void whenDeleteThenReturnSucess() {
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

        when(dataService.delete(anyLong())).thenReturn(true);

        ResponseEntity<CommercializationDTO> response = controller.delete(dto.getId());

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(dataService, times(1)).delete(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void startCommercializations()
    {
        commercialization = new Commercialization(ID,AREA_CUSTEIO, NOME_PRODUTO,NOME_UF, MES_EMISSAO, ANO_EMISSAO, CD_PROGRAMA, CD_SUB_PROGRAMA, CD_FONTE_RECURSO, CD_TIPO_SEGURO, QTD_CUSTEIO,VL_CUSTEIO, ATIVIDADE, CD_MODALIDADE);
        commercializationDTO = new CommercializationDTO(ID,AREA_CUSTEIO, NOME_PRODUTO,NOME_UF, MES_EMISSAO, ANO_EMISSAO, CD_PROGRAMA,CD_SUB_PROGRAMA, CD_FONTE_RECURSO, CD_TIPO_SEGURO, QTD_CUSTEIO,VL_CUSTEIO, ATIVIDADE, CD_MODALIDADE);;
    }

}
