package com.rocketmiles.exam.service;

import com.rocketmiles.exam.model.Register;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterServiceImplTest {

    private Register register;
    private RegisterService registerService;

    @BeforeEach
    public void init() {
        register = new Register();
        registerService = new RegisterServiceImpl();
    }

    @Test
    public void testPutCash() {
        String result = registerService.putCash(register, Arrays.asList(1,2,3,4,5));
        assertEquals("$68 1 2 3 4 5", result);
    }

    @Test
    public void testTakeCash() {
        String result = registerService.takeCash(register, Arrays.asList(1,2,3,4,5));
        assertEquals("Insufficient number of bills.", result);
    }

    @Test
    public void testShowCash() {
        String result = registerService.showCash(register);
        assertEquals("$0 0 0 0 0 0", result);
    }

    @Test
    public void testGiveChange() {
        String result = registerService.giveChange(register, 10);
        assertEquals("sorry", result);
    }

    @Test
    public void testGetTotalAmount() {
        assertEquals(0, registerService.getTotalAmount(register), "Initial total amount should be 0.");
    }

    @Test
    public void testPutCashAfterPutCash() {
        String result = registerService.putCash(register, Arrays.asList(1,2,3,4,5));
        assertEquals("$68 1 2 3 4 5", result);

        String result2 = registerService.putCash(register, Arrays.asList(1,2,3,0,5));
        assertEquals("$128 2 4 6 4 10", result2);
    }

    @Test
    public void testTakeCashAfterPutCash() {
        String result = registerService.putCash(register, Arrays.asList(2,4,6,4,10));
        assertEquals("$128 2 4 6 4 10", result);

        String result2 = registerService.takeCash(register, Arrays.asList(1,4,3,0,10));
        assertEquals("$43 1 0 3 4 0", result2);
    }

    @Test
    public void testShowCashAfterPutCash() {
        String result = registerService.putCash(register, Arrays.asList(1,2,3,4,5));
        assertEquals("$68 1 2 3 4 5", result);

        String result2 = registerService.showCash(register);
        assertEquals("$68 1 2 3 4 5", result2);
    }

    @Test
    public void testGiveChangeAfterPutCash() {
        String result = registerService.putCash(register, Arrays.asList(1,0,3,4,0));
        assertEquals("$43 1 0 3 4 0", result);

        String result2 = registerService.giveChange(register, 11);
        assertEquals("0 0 1 3 0", result2);

        String result3 = registerService.showCash(register);
        assertEquals("$32 1 0 2 1 0", result3);

        String result4 = registerService.giveChange(register, 14);
        assertEquals("sorry", result4);
    }
}