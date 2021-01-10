package com.rocketmiles.exam.model;

import java.util.List;

/**
 * Represents the command to be executed
 *
 * @author sonny
 */
public class RegisterCommand {

    private RegisterCommandType commandType;

    private List<Integer> commandParams;

    // Getters and setters

    public RegisterCommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(RegisterCommandType commandType) {
        this.commandType = commandType;
    }

    public List<Integer> getCommandParams() {
        return commandParams;
    }

    public void setCommandParams(List<Integer> commandParams) {
        this.commandParams = commandParams;
    }
}
