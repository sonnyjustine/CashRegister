package com.rocketmiles.exam.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DenominationTypeTest {

    @Test
    public void testValueOf() {
        Assertions.assertEquals(DenominationType.TWENTY_DOLLAR, DenominationType.valueOf(20));
        assertEquals(DenominationType.TEND_DOLLAR, DenominationType.valueOf(10));
        assertEquals(DenominationType.FIVE_DOLLAR, DenominationType.valueOf(5));
        assertEquals(DenominationType.TWO_DOLLAR, DenominationType.valueOf(2));
        assertEquals(DenominationType.ONE_DOLLAR, DenominationType.valueOf(1));
    }

    @Test
    public void testValues() {
        DenominationType[] denominations = DenominationType.values();
        assertEquals(denominations[0],DenominationType.TWENTY_DOLLAR);
        assertEquals(denominations[1],DenominationType.TEND_DOLLAR);
        assertEquals(denominations[2],DenominationType.FIVE_DOLLAR);
        assertEquals(denominations[3],DenominationType.TWO_DOLLAR);
        assertEquals(denominations[4],DenominationType.ONE_DOLLAR);
    }
}