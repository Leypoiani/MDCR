package projeto.dados.credito.rural.mdcr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.service.DataService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/database")
@Api(value="Projeto dados crédito rural - CRUD")
@CrossOrigin(origins = "*")
public class DataController {
    @Autowired
    private DataService dataService;


    @GetMapping("/all")
    @ApiOperation(value = "BUSCA TODA A BASE")
    public ResponseEntity<List<CommercializationDTO>> getAll() {
        List<CommercializationDTO> c = dataService.getAll();
        return !c.isEmpty() ?
                ResponseEntity.ok(c):
                ResponseEntity.notFound().build();
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "BUSCA REGISTRO PELO ID")
    public ResponseEntity<CommercializationDTO> getById(@PathVariable("id") Long id) {
        try {
            CommercializationDTO c = dataService.getById(id);
            return ResponseEntity.ok(c);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    @ApiOperation(value = "INSERE NOVO REGISTRO NO BANCO DE DADOS")
    public ResponseEntity post(@RequestBody Commercialization commercialization){
            Commercialization c = dataService.insert(commercialization);
            URI location = getUri(c.getId());
            return c!= null ?
                    ResponseEntity.created(location).body(c):
                    ResponseEntity.noContent().build();

    }
    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("id").buildAndExpand(id).toUri();
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "ATUALIZA REGISTRO DO BANCO POR ID")
    public ResponseEntity<CommercializationDTO> update(@PathVariable("id") Long id, @RequestBody CommercializationDTO commercialization) {
        commercialization.setId(id);
        CommercializationDTO c = dataService.update(commercialization, id);
        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "DELETA REGISTRO POR ID")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boolean ok = dataService.delete(id);
        return ok ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

}
