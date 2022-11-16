package projeto.dados.credito.rural.mdcr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.dados.credito.rural.mdcr.model.dto.CommercializationDTO;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;
import projeto.dados.credito.rural.mdcr.model.repository.CustomRepository;
import projeto.dados.credito.rural.mdcr.model.repository.DataRepository;
import projeto.dados.credito.rural.mdcr.service.DataService;
import projeto.dados.credito.rural.mdcr.service.FilterService;

import java.util.List;
@RestController
@RequestMapping("api/v1/filter")
@Api(value="Projeto dados crédito rural - FILTERS")
@CrossOrigin(origins = "*")
public class FiltersController {
    @Autowired
    private DataService dataService;
    @Autowired
    CustomRepository customRepository;
    @Autowired
    private FilterService filterService;
    @Autowired
    private CommercializationDTO commercializationDTO;
    @GetMapping("/{id}")
    @ApiOperation(value = "Consultar dados de comercialização de produto, região e UF por ID")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try{
            commercializationDTO = dataService.getById(id);
            return ResponseEntity.ok(commercializationDTO);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/")
    @ApiOperation(value = "Consultar lista de recursos (Produto, Região e UF) (com paginação)")
    public ResponseEntity<List<CommercializationDTO>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
            List<CommercializationDTO> c = filterService.getAllPageable(PageRequest.of(page,size));
            return c != null ?
                    ResponseEntity.ok(c):
                    ResponseEntity.notFound().build();


    }

    @GetMapping("/consult3")
    @ApiOperation(value = "Consultar lista de comercialização de produto, região e UF (código e nome da\n" +
            "    região, código e nome de um produto, mês, ano, unidade federativa, mês, ano,\n" +
            "    etc)")
    public ResponseEntity<List<Commercialization>> find(
                                        @RequestParam(value = "nomeProduto", required = false) String nomeProduto,
                                        @RequestParam(value = "nomeRegiao", required = false) String nomeRegiao,
                                        @RequestParam(value = "nomeUf", required = false) String nomeUf,
                                        @RequestParam(value = "MesEmissao",required = false) String MesEmissao,
                                        @RequestParam(value = "AnoEmissao",required = false) String AnoEmissao,
                                        @RequestParam(value = "cdPrograma",required = false) String cdPrograma,
                                        @RequestParam(value = "cdSubPrograma",required = false) String cdSubPrograma,
                                        @RequestParam(value = "cdFonteRecurso",required = false) String cdFonteRecurso,
                                        @RequestParam(value = "cdTipoSeguro",required = false) String cdTipoSeguro,
                                        @RequestParam(value = "Atividade",required = false) String Atividade,
                                        @RequestParam(value = "cdModalidade",required = false) String cdModalidade
                                        )
    {
            List<Commercialization> c = this.customRepository.find(nomeProduto, nomeRegiao, nomeUf, MesEmissao, AnoEmissao,
                    cdPrograma, cdSubPrograma, cdFonteRecurso, cdTipoSeguro, Atividade, cdModalidade);
            return !c.isEmpty() ?
                    ResponseEntity.ok(c):
                    ResponseEntity.notFound().build();

    }
    @GetMapping("/year")
    @ApiOperation(value = "Consultar dados de comercialização de produto, região e UF por ano, e gere o\n" +
            "    //somatório acumulado do período de doze meses para os produtos (soja, feijão,\n" +
            "    //trigo, milho e cana de açucar")
    public ResponseEntity<List<String>> getByYear(
            @RequestParam("anoEmissao") String anoEmissao){
        List<String> c = filterService.findByYear(anoEmissao);
        return !c.isEmpty() ?
                ResponseEntity.ok(c):
                ResponseEntity.notFound().build();
    }

    @GetMapping("/keyword")
    @ApiOperation(value = "Consulta utilizando palavra chave, com RequestParam.")
    public ResponseEntity<List<Commercialization>> getWithKeyWord(@RequestParam ("KeyWord") String keyWord){
        List<Commercialization> c = filterService.findByKeyWord(keyWord);
        return c != null && !c.isEmpty()?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/keyword/{param}")
    @ApiOperation(value = "Consulta utilizando palavra chave, com PathVariable.")
    public ResponseEntity<List<Commercialization>> getWithParam(@PathVariable("param") String keyWord){
        List<Commercialization> c = filterService.findByKeyWord(keyWord);
        return c != null && !c.isEmpty()?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }

}
