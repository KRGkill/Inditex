package com.gft.inditex.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "brand_id")
    Long brandId;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;

    @Column(name = "price_list")
    Long priceList;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "priority")
    Long priority;

    @Column(name = "price")
    Double price;

    @Enumerated(EnumType.STRING)
    Currency curr;

}