package projeto.dados.credito.rural.mdcr.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FilterServiceTest {

    @InjectMocks
    private FilterService service;
    @Mock
    private DataRepository dataRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("gatAllPageable")
    void whenGetAllPageableThenReturnAnListOfCommercialization(){

        Integer page = 0;
        Integer size = 10;

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

        Pageable pageable = PageRequest.of(page, size);
        Page<Commercialization> page1 = new PageImpl<>(List.of(c), pageable, 1);
        when(dataRepository.findAll(pageable)).thenReturn(page1);
        List<Commercialization> response = (List<Commercialization>) dataRepository.findAll(pageable).stream().collect(Collectors.toList());
        assertThat(response, is(response));
        assertNotNull(response);
        assertEquals(ArrayList.class, response.getClass());
        assertEquals(Commercialization.class, response.get(0).getClass());

    }

    @Test
    @DisplayName("findByYear")
    void whenFindByYearThenReturnAnListOfStrings(){
        List<String> list = new ArrayList<>();
        when(dataRepository.findByYear(anyString())).thenReturn(List.<String>of(list.toString()));

        List<String> response = service.findByYear("2019").stream().toList();
        assertNotNull(response);
        assertNotNull(response.get(0));
        assertEquals(String.class, response.get(0).getClass());
    }
    @Test
    @DisplayName("findByKeyWord")
    void whenFindByKeyWordThenReturnAnListOfCommercialization(){
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

        when(dataRepository.search(anyString())).thenReturn(List.of(c));
        List<Commercialization> response = new ArrayList<>();
        response.add(c);
        service.findByKeyWord("MILHO");

        assertNotNull(response);
        assertNotNull(response.get(0));
        assertEquals(Commercialization.class, response.get(0).getClass());
        assertEquals("MILHO", response.get(0).getNomeProduto());
    }


}
