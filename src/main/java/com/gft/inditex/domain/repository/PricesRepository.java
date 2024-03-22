package com.gft.inditex.domain.repository;

import com.gft.inditex.domain.entity.Prices;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public interface PricesRepository {

    Optional<Prices> findPrice(LocalDateTime date, Long productId, Long brandId);

    Optional<Prices> findById(Long id);

}
