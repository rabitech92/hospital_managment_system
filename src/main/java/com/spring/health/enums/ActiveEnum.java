package com.spring.health.enums;

public enum ActiveEnum {
    ACTIVE(1),
    DELETE(2);

    private final int value;

    ActiveEnum(int value) {
    this.value=value;
    }

    public int getValue() {
        return value;
    }
}
