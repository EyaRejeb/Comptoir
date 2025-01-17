package comptoirs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import comptoirs.entity.Commande;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Integer> {


/**
 * Calcule le montant des articles commandés dans une commande
 * @param numeroCommande le numéro de la commande à traiter
 * @return le montant des articles commandés, en tenant compte de la remise
 */

 @Query("""
    SELECT SUM(l.quantite * p.prixUnitaire * (1 - c.remise))
    FROM Commande c
    JOIN c.lignes l
    JOIN l.produit p
    WHERE c.numero = :numeroCommande
""")
BigDecimal montantArticles(Integer numeroCommande);
    
/**
 * Récupère les informations des commandes d'un client (NUMERO_COMMANDE, PORT, MONTANT_ARTICLES)
 * @param codeClient le code du client
 * @return une liste de projections contenant les informations des commandes
 */
@Query("""
    SELECT c.numero AS numeroCommande, c.port AS port, SUM(l.quantite * p.prixUnitaire * (1 - c.remise)) AS montantArticles
    FROM Commande c
    JOIN c.lignes l
    JOIN l.produit p
    WHERE c.client.code = :codeClient
    GROUP BY c.numero, c.port
""")
List<MontantParCommande> findCommandesByClient(String codeClient);

}
