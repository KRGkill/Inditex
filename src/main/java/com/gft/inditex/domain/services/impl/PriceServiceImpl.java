package com.gft.inditex.domain.services.impl;

import com.gft.inditex.domain.entity.Prices;
import com.gft.inditex.domain.exceptions.NotFoundException;
import com.gft.inditex.domain.repository.PricesRepository;
import com.gft.inditex.domain.services.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PricesRepository pricesRepository;

    @Override
    public Prices findPrice(LocalDateTime date, Long productId, Long brandId) {
        return pricesRepository.findPrice(date, productId, brandId)
                .orElseThrow(() -> new NotFoundException(String.format("Price with productId(%s), brandId(%s), date(%s )", productId, brandId, date)));
    }
}
