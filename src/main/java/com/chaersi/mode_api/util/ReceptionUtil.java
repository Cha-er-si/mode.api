package com.chaersi.mode_api.util;

import com.chaersi.mode_api.repository.DocumentRequestRepository;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class ReceptionUtil {
    private final DocumentRequestRepository documentRequestRepository;

    public ReceptionUtil(DocumentRequestRepository documentRequestRepository) {
        this.documentRequestRepository = documentRequestRepository;
    }

    public String receptionNumberGeneration() {
        String receptionNumber;
        boolean isNumberExists;
        do {
            receptionNumber = generateRandomNumber();

            isNumberExists = documentRequestRepository.findByReceptionNumber(receptionNumber).isPresent();
        } while (isNumberExists);

        return receptionNumber;
    }

    private String generateRandomNumber() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(15);
        int firstNumber = secureRandom.nextInt(9) + 1;
        stringBuilder.append(firstNumber);

        for (int index = 0; index < 14; index++ ) {
            int randomNumber = secureRandom.nextInt(10);
            stringBuilder.append(randomNumber);
        }

        return stringBuilder.toString();
    }
}
