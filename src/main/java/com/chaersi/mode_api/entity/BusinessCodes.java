package com.chaersi.mode_api.entity;

public enum BusinessCodes {
    DOCUMENT_REQUEST("01", "DCR"),
    REGISTRATION("02", "RGSTR");

    private final String key;
    private final String label;

    // Constructor
    BusinessCodes(String key, String label) {
        this.key = key;
        this.label = label;
    }

    // Getters
    public String getKey() { return key; }
    public String getLabel() { return label; }

    // Optional: Search for an enum by its key
    public static BusinessCodes findByKey(String key) {
        for (BusinessCodes businessCodes : values()) {
            if (businessCodes.key.equals(key)) return businessCodes;
        }
        throw new IllegalArgumentException("No business code with key: " + key);
    }
}
