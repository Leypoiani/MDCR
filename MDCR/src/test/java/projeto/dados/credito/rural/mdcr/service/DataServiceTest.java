package projeto.dados.credito.rural.mdcr.service;

import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DataServiceTest {

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
    private DataService service;
    @Mock
    private  Commercialization commercialization;
    @Mock
    private  CommercializationDTO commercializationDTO;


    public Optional<Commercialization> optionalComercialization;
    @Mock
    private DataRepository dataRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        startCommercializations();
    }

    @Test
    @DisplayName("findAll")
    void whenFindAllThenReturnAnListOfCommercialization(){
        when(dataRepository.findAll()).thenReturn(List.of(commercialization));
        List<CommercializationDTO> response = service.getAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(CommercializationDTO.class, response.get(0).getClass());
    }

    @Test
    @DisplayName("findById")
    void whenFindByIdThenReturnCommmercializationInstance(){
        when(dataRepository.findById(anyLong())).thenReturn(optionalComercialization);
        CommercializationDTO response = service.getById(ID);
        assertNotNull(response);
        assertEquals(CommercializationDTO.class, response.getClass());

    }

    @Test
    @DisplayName("getByIdNotFoundException")
    void whenFindByIdThenReturnObjectNotFoundException(){
        when(dataRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(0,"ID não encontrado"));
        try{
            service.getById(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }

    @Test
    @DisplayName("insertPositiveCase")
    public void whenCreateThenReturnSucess() {

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

        when(dataRepository.save(any())).thenReturn(dto);

        Commercialization response = service.insert(dto);
        assertNotNull(response);
        assertEquals(CommercializationDTO.class, response.getClass());
        assertEquals(dto.getId(), response.getId());
    }
    @Test
    @DisplayName("insertNegativeCase")
    public void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(dataRepository.save(any())).thenReturn(commercializationDTO);
        try{
            optionalComercialization.get().setId(2L);
            service.insert(commercializationDTO);
        }catch (Exception e){
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Não foi possível criar o registro.", e.getMessage());
        }
    }
/*
    @Test
    @DisplayName("updateSucess")
    void whenUpdateThenReturnSucess(){

        CommercializationDTO dto = new CommercializationDTO();
        dto.setNomeProduto("troca");
        dto.setNomeRegiao("troca");
        dto.setNomeUF("troca");
        dto.setMesEmissao("troca");
        dto.setAnoEmissao("troca");
        dto.setCdPrograma("troca");
        dto.setCdSubPrograma("troca");
        dto.setCdFonteRecurso("troca");
        dto.setCdTipoSeguro("troca");
        dto.setQtdCusteio(1);
        dto.setVlCusteio(BigDecimal.valueOf(1.0));
        dto.setAtividade("troca");
        dto.setCdModalidade("troca");
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

        when(dataRepository.save(any())).thenReturn(c);
        CommercializationDTO response = service.update(dto, dto.getId());
        assertNotNull(response);
        assertEquals(Commercialization.class, response.getClass());
    }*/

    @Test
    @DisplayName("updateThenIntegrityViolationException")
    public void whenUpdateThenReturnAnDataIntegrityViolationException() {

        when(dataRepository.save(any())).thenReturn(commercializationDTO);
        try{
            optionalComercialization.get().setId(2L);
            service.update(commercializationDTO, 1L);
        }catch (Exception e){
            assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals("Não foi possível atualizar o registro.", e.getMessage());
        }
    }

    @Test
    @DisplayName("delete")
    void deleteWithSucess() {
        when(dataRepository.findById(anyLong())).thenReturn(optionalComercialization);
        doNothing().when(dataRepository).deleteById(anyLong());
        service.delete(ID);
        verify(dataRepository, times(1)).deleteById(anyLong());
    }
    @Test
    @DisplayName("deleteNotFound")
    void deleteWithObjectNotFoundException() {
        when(dataRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(null, "Objeto não encontrado."));
        try{
            service.delete(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }
    private void startCommercializations()
    {
        commercialization = new Commercialization(ID,AREA_CUSTEIO, NOME_PRODUTO,NOME_UF, MES_EMISSAO, ANO_EMISSAO, CD_PROGRAMA, CD_SUB_PROGRAMA, CD_FONTE_RECURSO, CD_TIPO_SEGURO, QTD_CUSTEIO,VL_CUSTEIO, ATIVIDADE, CD_MODALIDADE);
        commercializationDTO = new CommercializationDTO(ID,AREA_CUSTEIO, NOME_PRODUTO,NOME_UF, MES_EMISSAO, ANO_EMISSAO, CD_PROGRAMA,CD_SUB_PROGRAMA, CD_FONTE_RECURSO, CD_TIPO_SEGURO, QTD_CUSTEIO,VL_CUSTEIO, ATIVIDADE, CD_MODALIDADE);;
        optionalComercialization = Optional.of(new Commercialization(ID, AREA_CUSTEIO, NOME_PRODUTO, NOME_UF, MES_EMISSAO, ANO_EMISSAO, CD_PROGRAMA, CD_SUB_PROGRAMA, CD_FONTE_RECURSO, CD_TIPO_SEGURO, QTD_CUSTEIO, VL_CUSTEIO, ATIVIDADE, CD_MODALIDADE));
    }

}
