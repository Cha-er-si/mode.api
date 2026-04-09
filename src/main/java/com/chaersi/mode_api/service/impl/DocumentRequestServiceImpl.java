package com.chaersi.mode_api.service.impl;

import com.chaersi.mode_api.dto.request.DocumentRequestDTO;
import com.chaersi.mode_api.dto.response.DocumentRequestResponse;
import com.chaersi.mode_api.entity.DocumentRequest;
import com.chaersi.mode_api.repository.DocumentRequestRepository;
import com.chaersi.mode_api.service.DocumentRequestService;
import com.chaersi.mode_api.util.ReceptionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentRequestServiceImpl implements DocumentRequestService {
    private final DocumentRequestRepository documentRequestRepository;
    private final ReceptionUtil receptionUtil;

    @Override
    public DocumentRequestResponse request(String applicationId, DocumentRequestDTO documentRequestDTO) {
        String receptionNumber = receptionUtil.receptionNumberGeneration();
        String firstName = documentRequestDTO.getFirstName();

        try {
            DocumentRequest documentRequest = DocumentRequest
                    .builder()
                    .applicationId(applicationId)
                    .receptionNumber(receptionNumber)
                    .firstName(documentRequestDTO.getFirstName())
                    .middleName(documentRequestDTO.getMiddleName())
                    .lastName(documentRequestDTO.getLastName())
                    .birthDate(documentRequestDTO.getBirthDate())
                    .houseNumber(documentRequestDTO.getHouseNumber())
                    .buildingNumber(documentRequestDTO.getBuildingNumber())
                    .street(documentRequestDTO.getStreet())
                    .baranggay(documentRequestDTO.getBaranggay())
                    .city(documentRequestDTO.getCity())
                    .region(documentRequestDTO.getRegion())
                    .zipCode(documentRequestDTO.getZipCode())
                    .country(documentRequestDTO.getCountry())
                    .documentType(documentRequestDTO.getDocumentType())
                    .purpose(documentRequestDTO.getPurpose())
                    .build();
            documentRequestRepository.save(documentRequest);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }


        DocumentRequestResponse documentRequestResponse = DocumentRequestResponse
                .builder()
                .status(200)
                .receptionNumber(receptionNumber)
                .build();

        return documentRequestResponse;
    }
}
