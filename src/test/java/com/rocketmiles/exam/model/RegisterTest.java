package com.rocketmiles.exam.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {

    private static Register register;

    @BeforeAll
    static void init() {
        register = new Register();
    }

    @Test
    public void testInitialCashMapDenominationCountIsZero() {
        Map<DenominationType, Integer> cashMap = register.getCashMap();

        for(DenominationType type : DenominationType.values()) {
            assertEquals(0, cashMap.get(type), "Initial count should be 0");
        }
    }
}