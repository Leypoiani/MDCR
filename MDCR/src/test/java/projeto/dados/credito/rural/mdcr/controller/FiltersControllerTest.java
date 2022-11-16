package projeto.dados.credito.rural.mdcr.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.CustomRepository;
import projeto.dados.credito.rural.mdcr.service.DataService;
import projeto.dados.credito.rural.mdcr.service.FilterService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FiltersControllerTest {

    @InjectMocks
    FiltersController controller;

    @Mock
    private DataService dataService;

    @Mock
    CustomRepository repository;

    @Mock
    private FilterService filterService;

    @Test
    @DisplayName("getByIdSucess")
    void whenGetByIdThenReturnSucess(){

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

            assertThat(response.getStatusCode(),is(HttpStatus.OK));
            assertEquals(123456712L, response.getBody().getId());
    }

    @Test
    @DisplayName("gatAllPageable")
    void whenGetAllPageableThenReturnSucess(){

        Integer page = 0;
        Integer size = 10;

        List<CommercializationDTO> lista = new ArrayList<>();

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

        lista.add(dto);
        Pageable pageable = PageRequest.of(page, size);
        when(filterService.getAllPageable(pageable)).thenReturn(lista);
        ResponseEntity<List<CommercializationDTO>> retorno= controller.getAll(0, 10);
        assertThat(retorno, is(retorno));

    }
    @Test
    @DisplayName("getConsult3")
    void whenFindByQueryThenReturnSucess() {
        List<Commercialization> lista = new ArrayList<>();

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

        lista.add(c);

        when(repository.find(anyString(), anyString(), anyString(),anyString(),
                anyString(),anyString(),anyString(),anyString(),anyString(),anyString(),
                anyString())).thenReturn(List.of(c));
        List<Commercialization> response = repository.find("teste","teste",
                "teste", "teste", "teste", "teste",
                "teste", "teste", "teste", "teste",
                "teste");
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertEquals(Commercialization.class, response.get(0).getClass());
    }
    @Test
    @DisplayName("getByYear")
    void whenGetByQueryThenReturnSucess(){
        List<String> list = new ArrayList<>();
        when(filterService.findByYear(anyString())).thenReturn(List.<String>of(list.toString()));

        List<String> response = filterService.findByYear("2019").stream().toList();
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertEquals(String.class, response.get(0).getClass());
    }
    @Test
    @DisplayName("getByKeyWord")
    void whenGetByKeyWordThenReturnSucess(){
        List<Commercialization> lista = new ArrayList<>();

        Commercialization c = new Commercialization();
        c.setId(123456712L);
        c.setNomeProduto("MILHO");
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

        lista.add(c);

        when(filterService.findByKeyWord(anyString())).thenReturn(List.of(c));

        List<Commercialization> response = filterService.findByKeyWord("MILHO");
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertEquals(Commercialization.class, response.get(0).getClass());
    }
    @Test
    @DisplayName("testNotFound")
    void whenReturnNotFound(){

        ResponseEntity<HttpStatus> response = new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
