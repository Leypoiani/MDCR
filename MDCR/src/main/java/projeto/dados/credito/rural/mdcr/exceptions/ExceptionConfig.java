package projeto.dados.credito.rural.mdcr.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionConfig {
@ExceptionHandler({
        DataIntegrityViolationException.class,
        RuntimeException.class

})
    public ResponseEntity errorNotFound(Exception e){
        return ResponseEntity.notFound().build();
    }
}
