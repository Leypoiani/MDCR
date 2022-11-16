package projeto.dados.credito.rural.mdcr.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;

import java.util.List;

public interface DataRepository extends JpaRepository<Commercialization, Long> {

   @Query(nativeQuery = true, value = "SELECT * FROM Commercialization c " +
           "WHERE CONCAT(c.nomeProduto, c.nomeRegiao, c.nomeUf, c.anoEmissao) LIKE %?1%")
   List<Commercialization> search(String keyWord);

   @Query(nativeQuery = true, value= "SELECT " +
        "c.nomeProduto nomeProduto, c.anoEmissao anoEmissao, " +
        "SUM(c.vlCusteio) vlTotal FROM Commercialization c " +
        "WHERE c.anoEmissao = :anoEmissao AND c.nomeProduto " +
        "IN ('\"SOJA\"', '\"MILHO\"', '\"TRIGO\"', '\"FEIJAO\"', '\"CANA DE AÇUCAR\"') " +
        "GROUP BY c.anoEmissao, c.nomeProduto")
   List<String> findByYear(String anoEmissao);
}
