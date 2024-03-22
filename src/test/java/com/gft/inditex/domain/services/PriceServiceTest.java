package com.gft.inditex.domain.services;

import com.gft.inditex.application.services.PriceService;
import com.gft.inditex.domain.entity.Prices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PriceServiceTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private PriceService priceService;

    @Test
    public void findPrice() {
        Prices prices = priceService.findPrice(LocalDateTime.parse("2020-06-14 10:00:00", formatter), 35455L, 1L);
        assertEquals(1L, prices.getId());
        assertEquals(35455L, prices.getProductId());
        assertEquals(1L, prices.getBrandId());
        assertEquals(1L, prices.getPriceList());
        assertEquals(35.50D, prices.getPrice());
        assertEquals("EUR", prices.getCurr().toString());
    }
}
