package projeto.dados.credito.rural.mdcr.exceptions;

import javassist.NotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;
import projeto.dados.credito.rural.mdcr.service.DataService;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ExceptionConfigTest {

    @InjectMocks
    ExceptionConfig exceptionConfig;

    @Mock
    DataRepository repository;
    @Mock
    DataService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void errorNotFound() {
        when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundException(0,"ID não encontrado"));
        try{
            service.getById(1L);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
        }
    }
}