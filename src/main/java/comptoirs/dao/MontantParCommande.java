package comptoirs.dao;

import java.math.BigDecimal;

//Interface implémentée pour pouvoir fin de projection des données (Question 2) 
public interface MontantParCommande {
    Integer getNumeroCommande();
    BigDecimal getPort();
    BigDecimal getMontantArticles();
}
