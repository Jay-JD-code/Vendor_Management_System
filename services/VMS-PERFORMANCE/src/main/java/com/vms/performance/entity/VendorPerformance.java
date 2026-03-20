package com.vms.performance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vendor_performance")
@Data
public class VendorPerformance {

    @Id
    private String vendorId;

    private int totalOrders;

    private int completedOrders;

    private int onTimeDeliveries;

    private double averageDeliveryTime;

    private double rating;
}
