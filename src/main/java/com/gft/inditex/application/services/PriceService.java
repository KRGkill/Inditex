package com.gft.inditex.application.services;

import com.gft.inditex.domain.entity.Prices;

import java.time.LocalDateTime;

public interface PriceService {

    Prices findPrice(LocalDateTime date, Long productId, Long brandId);

}
