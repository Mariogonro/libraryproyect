package com.ada.library.common;

public enum Status {
    ACTIVO('A', "Activo"),
    BLOQUEADO('B', "Bloqueado"),
    INACTIVO('I', "Inactivo");

    private final char code;
    private final String description;

    Status(char code, String description) {
        this.code = code;
        this.description = description;
    }

    public char getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status fromCode(char code) {
        for (Status status : Status.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de estado no válido: " + code);
    }
}
