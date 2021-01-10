package com.rocketmiles.exam.service;

import com.rocketmiles.exam.model.DenominationType;
import com.rocketmiles.exam.model.Register;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of RegisterService
 *
 * @author sonny
 */
public class RegisterServiceImpl implements RegisterService {

    private final List<DenominationType> denominations;

    private Map<DenominationType, Integer> changeMap;

    public RegisterServiceImpl() {
        this.denominations = Arrays.asList(DenominationType.values());
    }

    @Override
    public String putCash(Register register, List<Integer> cashCount) {
        for(int i = 0; i < denominations.size(); i++) {
            DenominationType key = denominations.get(i);
            int currentCount = register.getCashMap().get(key);
            register.getCashMap().put(key, currentCount + cashCount.get(i));
        }
        return this.showCash(register);
    }

    @Override
    public String takeCash(Register register, List<Integer> cashCount) {
        Map<DenominationType, Integer> tempMap = new HashMap<>(register.getCashMap());

        for(int i = 0; i < denominations.size(); i++) {
            DenominationType key = denominations.get(i);
            int currentCount = register.getCashMap().get(key);
            int count = currentCount - cashCount.get(i);
            if(count < 0) {
                return "Insufficient number of bills.";
            } else {
                tempMap.put(key, count);
            }
        }

        register.setCashMap(tempMap);
        return showCash(register);
    }

    @Override
    public String showCash(Register register) {
        String cashMapStr = denominations.stream()
                .map(key -> register.getCashMap().get(key).toString())
                .collect(Collectors.joining(" "));

        return "$" + this.getTotalAmount(register) + " " + cashMapStr;
    }

    @Override
    public String giveChange(Register register, Integer change) {
        List<Integer> allBills = new ArrayList<>();
        for(DenominationType denomination : denominations) {
            for(int i = 0; i < register.getCashMap().get(denomination); i++) {
                allBills.add(denomination.getValue());
            }
        }

        changeMap = new HashMap<>();
        // Check if the given change amount can be represented as a sum of the bills present in the register
        if(checkIfPossibleSum(change, allBills)) {
            List<Integer> takeCash = new ArrayList<>();
            for(DenominationType denomination : denominations) {
                Integer value = changeMap.get(denomination);
                takeCash.add(value == null ? 0 : value);
            }
            // Take cash from the register if change can be provided
            this.takeCash(register, takeCash).split(" ", 2);
            return denominations.stream()
                    .map(key -> changeMap.get(key) == null ? "0" : changeMap.get(key).toString())
                    .collect(Collectors.joining(" "));
        } else {
            return "sorry";
        }
    }

    @Override
    public Integer getTotalAmount(Register register) {
        int amount = 0;
        for(DenominationType key : denominations) {
            amount += key.getValue() * register.getCashMap().get(key);
        }
        return amount;
    }

    /**
     * Check if a given number can be represented as a sum of numbers in a given list
     * For example, given sum = 2 & numbers = [5,5,5,1,1], this function will return true since 2 can be represented as sum of [1,1]
     * @param sum Given sum
     * @param numbers List of numbers
     * @see <a href="https://stackoverflow.com/a/34457153">Based on this algorithm</a>
     *
     * @return true if possible, false if not
     */
    private boolean checkIfPossibleSum(int sum, List<Integer> numbers) {
        changeMap = new HashMap<>();

        // return false if list is already empty
        if(numbers.size() == 0) {
            return false;
        }

        while(numbers.get(0) > sum) {
            // remove element in list if it is greater than the given sum
            numbers.remove(0);
            if(numbers.size() == 0) {
                return false;
            }
        }

        // return true if the last element is already equal to the given sum
        if(numbers.get(0) == sum) {
            int currentCount = 0;
            DenominationType key = DenominationType.valueOf(numbers.get(0));
            if(changeMap.get(key) != null) currentCount = changeMap.get(key);
            changeMap.put(key, currentCount + 1);
            return true;
        }

        int i = 0;
        for(Integer num : numbers) {
            List<Integer> sublist = new ArrayList<>(numbers.subList(i+1, numbers.size()));
            // while iterating through the list of numbers, subtract the current number to the sum and use it as the new sum
            // remove the subtracted number from the list and use it as the new numbers list
            if(checkIfPossibleSum(sum - num, sublist)) {
                int currentCount = 0;
                DenominationType key = DenominationType.valueOf(num);
                if(changeMap.get(key) != null) currentCount = changeMap.get(key);
                changeMap.put(key, currentCount + 1);
                return true;
            }

        }

        return false;
    }
}
