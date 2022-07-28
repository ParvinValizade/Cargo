package com.company.parceldeliveryapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Parcel Delivery App",
                description = "Cargo",
                version = "v1"
        )
)
public class ParcelDeliveryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcelDeliveryAppApplication.class, args);
    }

}
