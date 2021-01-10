package com.rocketmiles.exam;

/**
 * Wraps all unchecked RuntimeExceptions and enriches them with custom error message
 *
 * @author sonny
 */
public class CustomRegisterException extends RuntimeException {

    public CustomRegisterException(String message) {
        super(message);
    }

}
