package projeto.dados.credito.rural.mdcr.feignClient;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projeto.dados.credito.rural.mdcr.model.entitiy.CommercializationList;

@org.springframework.cloud.openfeign.FeignClient(name = "Commercialization", url = "https://olinda.bcb.gov.br/olinda/servico/SICOR/versao/v2/odata/ComercRegiaoUFProduto?$top=1000&$format=json")

public interface FeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "")
    CommercializationList getallCommercialization();

}
