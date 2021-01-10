package com.rocketmiles.exam.model;

/**
 * Types of Denomination accepted by the Register application
 */
public enum DenominationType {
    TWENTY_DOLLAR(20),
    TEND_DOLLAR(10),
    FIVE_DOLLAR(5),
    TWO_DOLLAR(2),
    ONE_DOLLAR(1);

    private final int value;

    DenominationType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DenominationType valueOf(int num) {
        for (DenominationType type : values()) {
            if (type.value == num) {
                return type;
            }
        }
        throw new IllegalArgumentException("No denomination of " + num);
    }
}
