package com.rocketmiles.exam.converter;

import com.rocketmiles.exam.CustomRegisterException;
import com.rocketmiles.exam.model.RegisterCommand;
import com.rocketmiles.exam.model.RegisterCommandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Converts a given String to a RegisterCommand object
 *
 * @author sonny
 */
public class RegisterCommandConverter implements StringToObjectConverter<RegisterCommand> {

    @Override
    public RegisterCommand convert(String inputStr) {
        RegisterCommand command = new RegisterCommand();

        if(inputStr != null && !inputStr.isEmpty()) {
            String[] inputList = inputStr.split(" ", 2);

            String commandStr = inputList[0];

            // Set command type
            command.setCommandType(RegisterCommandType.valueOf(commandStr.toUpperCase()));

            if(inputList.length > 1) {
                String[] commandParamsArray = inputList[1].split(" ");
                switch(command.getCommandType()) {
                    case PUT:
                    case TAKE:
                        if(commandParamsArray.length != 5) {
                            throw new CustomRegisterException("Incorrect number of parameters.");
                        }
                        break;
                    case SHOW:
                    case QUIT:
                        if(commandParamsArray.length > 0) {
                            throw new CustomRegisterException("No parameter needed for this command.");
                        }
                        break;
                    case CHANGE:
                        if(commandParamsArray.length != 1) {
                            throw new CustomRegisterException("Incorrect number of parameters.");
                        }
                        break;
                }

                // Set command params / values needed for the command to be executed
                List<Integer> commandParams = new ArrayList<>();
                for(String param : Arrays.asList(commandParamsArray)) {
                    if(Integer.parseInt(param) > -1) {
                        commandParams.add(Integer.parseInt(param));
                    } else {
                        // Throw exception if value is negative
                        throw new CustomRegisterException("Invalid parameter. Register does not accept negative values.");
                    }
                }
                command.setCommandParams(commandParams);
            }
        } else {
            throw new CustomRegisterException("Command is empty. Please enter a command.");
        }

        return command;
    }
}