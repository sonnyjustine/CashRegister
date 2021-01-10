package com.rocketmiles.exam.converter;

import com.rocketmiles.exam.CustomRegisterException;
import com.rocketmiles.exam.model.RegisterCommand;
import com.rocketmiles.exam.model.RegisterCommandType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterCommandConverterTest {

    private static RegisterCommandConverter converter;

    @BeforeAll
    static void init() {
        converter = new RegisterCommandConverter();
    }

    @Test
    void testConvertValidPutCommand() {
        RegisterCommand command = converter.convert("put 1 2 3 4 5");
        assertEquals(RegisterCommandType.PUT, command.getCommandType());
        assertEquals(Arrays.asList(1,2,3,4,5),command.getCommandParams());
    }

    @Test
    void testConvertValidTakeCommand() {
        RegisterCommand command = converter.convert("take 5 4 3 2 1");
        assertEquals(RegisterCommandType.TAKE, command.getCommandType());
        assertEquals(Arrays.asList(5,4,3,2,1),command.getCommandParams());
    }

    @Test
    void testConvertValidShowCommand() {
        RegisterCommand command = converter.convert("show");
        assertEquals(RegisterCommandType.SHOW, command.getCommandType());
        assertNull(command.getCommandParams());
    }

    @Test
    void testConvertValidChangeCommand() {
        RegisterCommand command = converter.convert("change 10");
        assertEquals(RegisterCommandType.CHANGE, command.getCommandType());
        assertEquals(Arrays.asList(10),command.getCommandParams());
    }

    @Test
    void testConvertValidQuitCommand() {
        RegisterCommand command = converter.convert("quit");
        assertEquals(RegisterCommandType.QUIT, command.getCommandType());
        assertNull(command.getCommandParams());
    }

    @Test
    void testConvertThrowExceptionIfCommandIsInvalid() {
        assertThrows(IllegalArgumentException.class,() -> converter.convert("test command"));
    }

    @Test
    void testConvertThrowExceptionIfCommandIsEmpty() {
        CustomRegisterException thrown = assertThrows(
                CustomRegisterException.class,
                () -> converter.convert("")
        );

        assertEquals("Command is empty. Please enter a command.", thrown.getMessage());
    }

    @Test
    void testConvertThrowExceptionIfPutNegativeValue() {
        CustomRegisterException thrown = assertThrows(
                CustomRegisterException.class,
                () -> converter.convert("put 0 -1 5 5 5")
        );

        assertEquals("Invalid parameter. Register does not accept negative values.", thrown.getMessage());
    }

    @Test
    void testConvertThrowExceptionIfPutIncorrectNumberOfParam() {
        CustomRegisterException thrown = assertThrows(
                CustomRegisterException.class,
                () -> converter.convert("put 0 1 1 1")
        );

        assertEquals("Incorrect number of parameters.", thrown.getMessage());
    }

    @Test
    void testConvertThrowExceptionIfShowIncorrectNumberOfParam() {
        CustomRegisterException thrown = assertThrows(
                CustomRegisterException.class,
                () -> converter.convert("show 0 1 1 1")
        );

        assertEquals("No parameter needed for this command.", thrown.getMessage());
    }

    @Test
    void testConvertThrowExceptionIfChangeIncorrectNumberOfParam() {
        CustomRegisterException thrown = assertThrows(
                CustomRegisterException.class,
                () -> converter.convert("change 0 1")
        );

        assertEquals("Incorrect number of parameters.", thrown.getMessage());
    }
}