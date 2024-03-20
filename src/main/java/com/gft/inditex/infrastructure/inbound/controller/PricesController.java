package com.gft.inditex.infrastructure.inbound.controller;

import com.gft.inditex.domain.entity.Prices;
import com.gft.inditex.domain.exceptions.NotFoundException;
import com.gft.inditex.domain.services.PriceService;
import com.gft.inditex.infrastructure.model.GetPriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Tag(name = "Prices", description = "Endpoints for Prices")
@RestController
@RequestMapping("/api")
public class PricesController {

    @Autowired
    private PriceService priceService;

    ModelMapper mapper = new ModelMapper();

    @Operation(
            summary = "Get the Price of a Product",
            description = "Get the price by Product, Brand and Date"
    )
    @ApiResponse(responseCode = "200", description = "Price Found",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GetPriceResponse.class))})
    @ApiResponse(responseCode = "404", description = "Price Not Found",
            content = {@Content(schema = @Schema())})
    @GetMapping("/price")
    public ResponseEntity<GetPriceResponse> getPrice(
            @Parameter(example = "2020-06-15 00:00:00") @RequestParam LocalDateTime date,
            @Parameter(example = "35455") @RequestParam Long productId,
            @Parameter(example = "1") @RequestParam Long brandId) {
        try {
            Prices price = priceService.findPrice(date, productId, brandId);
            return ResponseEntity.ok(mapper.map(price, GetPriceResponse.class));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
