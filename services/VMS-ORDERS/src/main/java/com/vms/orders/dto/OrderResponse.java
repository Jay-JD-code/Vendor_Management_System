package com.vms.orders.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderResponse {

    private Long orderId;
    private String vendorId;
    private String status;
    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;
}
