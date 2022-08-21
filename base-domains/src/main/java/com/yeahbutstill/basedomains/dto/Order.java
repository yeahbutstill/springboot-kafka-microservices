package com.yeahbutstill.basedomains.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private String orderId;
    private String name;
    private Integer quantity;
    private Double price;

}
