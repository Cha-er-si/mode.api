package com.chaersi.mode_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "document_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentRequest {
    @Id
    @Column(nullable = false, unique = true)
    private String applicationId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String receptionNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(columnDefinition = "TEXT")
    private String middleName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(nullable = false, columnDefinition = "DATE")
    private Date birthDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String houseNumber;

    private String buildingNumber;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String street;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String baranggay;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String city;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String region;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String country;

    @Column(nullable = false, columnDefinition = "INT")
    private String zipCode;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String documentType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String purpose;
}
