package projeto.dados.credito.rural.mdcr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.dados.credito.rural.mdcr.model.entitiy.CommercializationList;
import projeto.dados.credito.rural.mdcr.feignClient.FeignClient;
import projeto.dados.credito.rural.mdcr.service.CommercializationService;

@RestController
@RequestMapping("api/v1/banco-central")
@Api(value="Projeto dados crédito rural - IMPORT")
@CrossOrigin(origins = "*")
public class CommercializationController {
    @Autowired
    FeignClient feignClient;
    @Autowired
    CommercializationService commercializationService;

    @GetMapping("/get")
    @ApiOperation(value = "Busca a api do banco central.")
    public ResponseEntity<CommercializationList> getCommercialization(){
            CommercializationList c = feignClient.getallCommercialization();
        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();

    }

    @PostMapping("/save")
    @ApiOperation(value = "Paga a base do banco central com base em parâmetros e popula o banco de dados local.")
    public ResponseEntity save() {
        try{
            feignClient.getallCommercialization().getValue().stream().forEach(x -> commercializationService.save(x));
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
