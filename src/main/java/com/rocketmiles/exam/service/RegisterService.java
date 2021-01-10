package com.rocketmiles.exam.service;

import com.rocketmiles.exam.model.Register;

import java.util.List;

/**
 * Service that handles Register operations
 *
 * @author sonny
 */
public interface RegisterService {

    /**
     * Add cash to the Register
     * @param register the register object
     * @param cashCount List that contains the number of bills to be added to the register.
     * It is assumed that the count defined in the list corresponds to the denomination list (20,10,5,2,1)
     * @return showCash()
     */
    String putCash(Register register, List<Integer> cashCount);

    /**
     * Take cash from the Register
     * @param register the register object
     * @param cashCount List that contains the number of bills to be taken from the register.
     * It is assumed that the count defined in the list corresponds to the denomination list (20,10,5,2,1)
     * @return showCash()
     * returns custom message if number of bills in the Register is not sufficient
     */
    String takeCash(Register register, List<Integer> cashCount);

    /**
     * Print details of the Register
     * @param register the register object
     * @return a String that contains the total amount of all the bills in the Register + the total count of bills for each denomination
     */
    String showCash(Register register);

    /**
     * Computes the number of bills to be given from the cash register based on a given change amount
     * @param register the register object
     * @param change the amount of change
     * @return a String that shows number of bills per denomination (20,10,5,2,1)
     * returns custom message if change cannot be provided
     */
    String giveChange(Register register, Integer change);

    /**
     * Compute total amount of money in the register
     * @param register the register object
     * @return int total amount
     */
    Integer getTotalAmount(Register register);
}
