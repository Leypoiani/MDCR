package projeto.dados.credito.rural.mdcr.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import projeto.dados.credito.rural.mdcr.feignClient.FeignClient;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.entitiy.CommercializationList;
import projeto.dados.credito.rural.mdcr.service.CommercializationService;
import projeto.dados.credito.rural.mdcr.service.CommercializationServiceTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommercializationControllerTest {
    @InjectMocks
    CommercializationController controller;

    @Mock
    FeignClient feignClient;

    @Mock
    CommercializationList commercialization;

    @Test
    public void getCommercializationTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("saveRegisters")
    void whenSaveCommercializationTheReturnSucess(){
        CommercializationList c = new CommercializationList();

        when(feignClient.getallCommercialization()).thenReturn(c);
        CommercializationList response = feignClient.getallCommercialization();

        assertNotNull(response);
        assertEquals(CommercializationList.class, response.getClass());
    }

}
