package com.gft.inditex.infrastructure.inbound.controller;

import com.gft.inditex.domain.entity.Prices;
import com.gft.inditex.domain.repository.PricesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PricesRepository pricesRepository;

    ModelMapper mapper = new ModelMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testGetPriceSuccessful(LocalDateTime date, Long productId, Long brandId, Long expectedId) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        Prices expectedPrice = pricesRepository.findById(expectedId).orElseThrow();

        mockMvc.perform(get("/api/price")
                        .param("date", String.valueOf(date))
                        .param("productId", String.valueOf(productId))
                        .param("brandId", String.valueOf(brandId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("productId").value(expectedPrice.getProductId()))
                .andExpect(jsonPath("brandId").value(expectedPrice.getBrandId()))
                .andExpect(jsonPath("priceList").value(expectedPrice.getPriceList()))
                .andExpect(jsonPath("startDate").value(expectedPrice.getStartDate().format(formatter)))
                .andExpect(jsonPath("endDate").value(expectedPrice.getEndDate().format(formatter)))
                .andExpect(jsonPath("price").value(expectedPrice.getPrice()));
    }

    private static Stream<Arguments> provideTestData() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Stream.of(
                Arguments.of(LocalDateTime.parse("2020-06-14 10:00:00", formatter), 35455L, 1L, 1L),
                Arguments.of(LocalDateTime.parse("2020-06-14 16:00:00", formatter), 35455L, 1L, 2L),
                Arguments.of(LocalDateTime.parse("2020-06-14 21:00:00", formatter), 35455L, 1L, 1L),
                Arguments.of(LocalDateTime.parse("2020-06-15 10:00:00", formatter), 35455L, 1L, 3L),
                Arguments.of(LocalDateTime.parse("2020-06-16 21:00:00", formatter), 35455L, 1L, 4L)
        );
    }
}

