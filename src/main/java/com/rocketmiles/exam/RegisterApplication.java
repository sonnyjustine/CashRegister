package com.rocketmiles.exam;

import com.rocketmiles.exam.converter.RegisterCommandConverter;
import com.rocketmiles.exam.model.Register;
import com.rocketmiles.exam.model.RegisterCommand;
import com.rocketmiles.exam.service.RegisterService;
import com.rocketmiles.exam.service.RegisterServiceImpl;

import java.util.Scanner;

/**
 * Rocketmiles Register Application
 * A register app that app that accepts $20, $10, $5, $2 and $1 bills.
 *
 * @author sonny
 */
public class RegisterApplication {

    public static void main(String[] args) {
        // Create Register
        Register register = new Register();

        // Initialize Services to be used
        RegisterService registerService = new RegisterServiceImpl();
        RegisterCommandConverter registerCommandConverter = new RegisterCommandConverter();

        // Inform user that application is now ready to be used
        System.out.println("ready");

        // Accept input from scanner
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String registerInput = sc.nextLine();
            String result = "";

            try {
                // Convert entered input from the user to a RegisterCommand object
                RegisterCommand command = registerCommandConverter.convert(registerInput);
                switch(command.getCommandType()) {
                    case PUT:
                        result = registerService.putCash(register, command.getCommandParams());
                        break;
                    case TAKE:
                        result = registerService.takeCash(register, command.getCommandParams());
                        break;
                    case SHOW:
                        result = registerService.showCash(register);
                        break;
                    case CHANGE:
                        result = registerService.giveChange(register, command.getCommandParams().get(0));
                        break;
                    case QUIT:
                        System.out.println("Bye");
                        sc.close();
                        System.exit(0);
                        break;
                    default:
                }

                // Print result
                System.out.println(result);
            } catch (CustomRegisterException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid register command parameter.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid register command.");
            }
        }
    }
}
