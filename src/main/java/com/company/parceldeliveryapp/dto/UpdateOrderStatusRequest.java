package com.company.parceldeliveryapp.dto;

public class UpdateOrderStatusRequest {

    private String status;

    public String getStatus() {
        return status;
    }

    public UpdateOrderStatusRequest(String status) {
        this.status = status;
    }
}
