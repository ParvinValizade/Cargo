package com.company.parceldeliveryapp.dto;

public class UpdateOrderDestinationRequest {
    private String destination;

    public String getDestination() {
        return destination;
    }

    public UpdateOrderDestinationRequest(String destination) {
        this.destination = destination;
    }
}
