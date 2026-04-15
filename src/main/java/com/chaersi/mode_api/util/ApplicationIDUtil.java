package com.chaersi.mode_api.util;

import com.chaersi.mode_api.entity.BusinessCodes;
import com.chaersi.mode_api.repository.ApplicationRequestRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class ApplicationIDUtil {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final ApplicationRequestRepository applicationRequestRepository;

    public ApplicationIDUtil(ApplicationRequestRepository requestRepository) {
        this.applicationRequestRepository = requestRepository;
    }

    public String generateApplicationID(String businessCode) {
        String businessCodePrefix = BusinessCodes.findByKey(businessCode).getLabel();
        String datePrefix = LocalDate.now().format(DATE_FORMATTER);
        Optional<String> lastIdOpt = applicationRequestRepository.findLastApplicationIdForDate(businessCodePrefix, datePrefix);
        int substringIndex = businessCodePrefix.length() + datePrefix.length();
        long nextNumber = lastIdOpt
                .map(id -> Long.parseLong(id.substring(substringIndex)) + 1) // take last 7 digits and increment
                .orElse(1L);

        return businessCodePrefix + datePrefix + String.format("%07d", nextNumber);
    }
}
