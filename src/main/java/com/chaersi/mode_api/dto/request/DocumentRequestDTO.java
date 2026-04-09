package com.chaersi.mode_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class DocumentRequestDTO {
    @NotBlank(message = "firstName is required.")
    @Size(min = 2, max = 35)
    private String firstName;

    private String middleName;

    @NotBlank(message = "lastName is required.")
    @Size(min = 2, max = 20)
    private String lastName;

    @NotNull(message = "birthDate is required.")
    private Date birthDate;

    @NotBlank(message = "houseNumber is required.")
    @Size(min = 2, max = 20)
    private String houseNumber;

    private String buildingNumber;

    @NotBlank(message = "street is required.")
    @Size(min = 2, max = 30)
    private String street;

    @NotBlank(message = "baranggay is required.")
    @Size(min = 2, max = 20)
    private String baranggay;

    @NotBlank(message = "city is required.")
    @Size(min = 2, max = 20)
    private String city;

    @NotBlank(message = "region is required.")
    @Size(min = 2, max = 20)
    private String region;

    @NotBlank(message = "country is required.")
    @Size(min = 2, max = 20)
    private String country;

    @NotBlank(message = "zipCode is required.")
    @Pattern(regexp = "^\\d+$", message = "zipCode must contain only digits")
    @Size(min = 4, max = 4)
    private String zipCode;

    @NotBlank(message = "documentType is required.")
    private String documentType;

    @NotBlank(message = "purpose is required.")
    private String purpose;
}
