package com.yeahbutstill.basedomains.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderEvent {

    private String message;
    private String status;
    private Order order;

}
