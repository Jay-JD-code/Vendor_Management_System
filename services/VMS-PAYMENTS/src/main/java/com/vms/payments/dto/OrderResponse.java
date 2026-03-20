package com.vms.payments.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private Long orderId;
    private String vendorId;
    private String status;
}
