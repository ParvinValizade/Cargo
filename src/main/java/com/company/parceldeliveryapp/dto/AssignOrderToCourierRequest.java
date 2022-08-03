package com.company.parceldeliveryapp.dto;

public class AssignOrderToCourierRequest {
    private String courierMail;

    public String getCourierMail() {
        return courierMail;
    }

    public AssignOrderToCourierRequest(String courierMail) {
        this.courierMail = courierMail;
    }
}
