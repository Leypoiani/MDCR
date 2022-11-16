package projeto.dados.credito.rural.mdcr.model.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import projeto.dados.credito.rural.mdcr.model.entitiy.Commercialization;

import javax.persistence.EntityManager;
import java.util.List;

@AllArgsConstructor
@Repository
public class CustomRepository {

    private final EntityManager em;

    public List<Commercialization> find(String nomeProduto,
                                        String nomeRegiao,
                                        String nomeUF,
                                        String MesEmissao,
                                        String AnoEmissao,
                                        String cdPrograma,
                                        String cdSubPrograma,
                                        String cdFonteRecurso,
                                        String cdTipoSeguro,
                                        String Atividade,
                                        String cdModalidade){

        String query = "SELECT id, nomeProduto, nomeRegiao, nomeUF, mesEmissao, anoEmissao, vlCusteio, " +
                "cdPrograma, cdSubPrograma, cdFonteRecurso, cdTipoSeguro, atividade, cdModalidade " +
                "FROM Commercialization ";
        String condicao = "WHERE";

    if(nomeProduto != null){
        query += condicao + " nomeProduto = :nomeProduto ";
        condicao = " and ";
    }
    if(nomeRegiao != null){
        query += condicao + " nomeRegiao = :nomeRegiao ";
        condicao = " and ";
    }
    if(nomeUF != null){
        query += condicao + " nomeUF = :nomeUF ";
        condicao = " and ";
    }
    if(MesEmissao != null){
        query += condicao + " mesEmissao = :mesEmissao ";
        condicao = " and ";
    }
    if(AnoEmissao != null){
        query += condicao + " anoEmissao = :anoEmissao ";
        condicao = " and ";
    }
    if(cdPrograma != null){
        query += condicao + " cdPrograma = :cdPrograma ";
        condicao = " and ";
    }
    if(cdSubPrograma != null){
        query += condicao + " cdSubPrograma = :cdSubPrograma ";
        condicao = " and ";
    }
    if(cdFonteRecurso != null){
        query += condicao + " cdFonteRecurso = :cdFonteRecurso ";
        condicao = " and ";
    }
    if(cdTipoSeguro != null){
        query += condicao + " cdTipoSeguro = :cdTipoSeguro ";
        condicao = " and ";
    }
    if(Atividade != null){
        query += condicao + " atividade = :atividade ";
        condicao = " and ";
    }
    if(cdModalidade != null){
        query += condicao + " cdModalidade = :cdModalidade ";
    }

    var q = em.createQuery(query);

        if(nomeProduto != null){
            q.setParameter("nomeProduto", nomeProduto);
        }
        if(nomeRegiao != null){
            q.setParameter("nomeRegiao", nomeRegiao);
        }
        if(nomeUF != null){
            q.setParameter("nomeUF", nomeUF);
        }
        if(MesEmissao != null){
            q.setParameter("mesEmissao", MesEmissao);
        }
        if(AnoEmissao != null){
            q.setParameter("anoEmissao", AnoEmissao);
        }
        if(cdPrograma != null){
            q.setParameter("cdPrograma", cdPrograma);
        }
        if(cdSubPrograma != null){
            q.setParameter("cdSubPrograma", cdSubPrograma);
        }
        if(cdFonteRecurso != null){
            q.setParameter("cdFonteRecurso", cdFonteRecurso);
        }
        if(cdTipoSeguro != null){
            q.setParameter("cdTipoSeguro", cdTipoSeguro);
        }
        if(Atividade != null){
            q.setParameter("atividade", Atividade);
        }
        if(cdModalidade != null){
            q.setParameter("cdModalidade", cdModalidade);
        }
        return q.getResultList();
    }
}
