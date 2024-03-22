package com.gft.inditex.infrastructure.repositoryimpl;

import com.gft.inditex.domain.entity.Prices;
import com.gft.inditex.domain.repository.PricesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class PricesRepositoryImpl implements PricesRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Prices> findPrice(LocalDateTime date, Long productId, Long brandId) {
        Query jpqlQuery = entityManager.createQuery(
                """
                        SELECT p
                        FROM Prices p
                        WHERE p.productId = :productId
                        AND p.brandId = :brandId
                        AND p.startDate <= :date
                        AND p.endDate >= :date
                        ORDER BY p.priority DESC
                        LIMIT 1
                        """, Prices.class);
        jpqlQuery.setParameter("date", date);
        jpqlQuery.setParameter("productId", productId);
        jpqlQuery.setParameter("brandId", brandId);
        return Optional.of((Prices) jpqlQuery.getSingleResult());
    }

    @Override
    public Optional<Prices> findById(Long id) {
        Query jpqlQuery = entityManager.createQuery(
                """
                        SELECT p
                        FROM Prices p
                        WHERE p.id = :id
                        """, Prices.class);
        jpqlQuery.setParameter("id", id);
        return Optional.of((Prices) jpqlQuery.getSingleResult());
    }

}
