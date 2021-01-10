package com.rocketmiles.exam.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.rocketmiles.exam.model.DenominationType.*;

/**
 * The Register object which contains the cashMap.
 * cashMap contains number of bills (value) present for each denomination (key)
 *
 * @author sonny
 */
public class Register {

    private Map<DenominationType, Integer> cashMap;

    public Register() {
        // initialize cashMap, set each denomination count to zero (0)
        this.cashMap = Stream.of(new Object[][] {
                {TWENTY_DOLLAR, 0}, {TEND_DOLLAR, 0}, {FIVE_DOLLAR, 0}, {TWO_DOLLAR, 0}, {ONE_DOLLAR, 0}
        }).collect(Collectors.toMap(data -> (DenominationType) data[0], data -> (Integer) data[1]));
    }

    // Getters and setters

    public Map<DenominationType, Integer> getCashMap() {
        return cashMap;
    }

    public void setCashMap(Map<DenominationType, Integer> cashMap) {
        this.cashMap = cashMap;
    }
}
