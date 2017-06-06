/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package coindispenser;

import java.util.ArrayList;
import java.util.List;


/**
 * Simulation of a device that dispenses coins.
 * 
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class CoinDispenser {

    private static final int[] VALUES = {200, 100, 50, 20,10, 5, 2, 1};
    int[] coinCounters;        

    /**
     * Create a new empty dispenser.
     */
    public CoinDispenser() {
        coinCounters = new int[VALUES.length];
        
    }    
    
    /**
     * Create a new dispenser.
     * 
     * @param initialCoins Initial number of coins of each type
     */
    public CoinDispenser(int initialCoins) {
        coinCounters = new int[VALUES.length];
        
        for (int i = 0; i < coinCounters.length; i++)
        {
                coinCounters[i] = initialCoins;
            
        }
    }
        
    /**
     * Increase the number of coins of a given coinValue.
     * 
     * @param coinValue type of coins to be added (in cents)
     * @param amount number of coins to add
     */
    public void addCoins(int coinValue, int amount) {
        int index = getValueIndex(coinValue);
        if (index >= 0 && amount >= 0)
            coinCounters[index] += amount;
    }
    
    /**
     * Return the number of available coins of a given type,
     * 
     * @param coinValue type of coins to be added (in cents)
     * @return the number of available coins for the specified coinValue
     */
    public int countCoins(int coinValue) {
        int index = getValueIndex(coinValue);
        if (index < 0)
            return 0;
        return coinCounters[index];
    }        
    
    /**
     * Compute a sequence of coins that can be used to form the requested value.
     * 
     * null is returned then it is not possible to form the value because of the
     * lack of coins, or because of other constraints.
     * 
     * @param value value (in cents) to be composed
     * @param maxCoins maximum number of coins that can be used to compose the value
     * @param maxExcess maximum allowed excess that can be tolerated in composing the value
     * @return the list of coins or null
     */
    public List<Integer> composeChange(int value, int maxCoins, int maxExcess) {
        ArrayList<Integer> result = new ArrayList<>();
        composeChangeHelper(value, maxCoins, maxExcess, result);
        return result;
    }
    
    /**
     * Update the count of the available coins, according to the list of coins
     * to dispense.
     * 
     * An exception is thrown when it is not possible to dispense the requested coins.
     * 
     * @param coins list of values representing the coins to dispense
     * @throws DispenserError 
     */
    public void dispenseCoins(List<Integer> coins) throws DispenserError {
        int[] newcounts= coinCounters.clone();
        for (int c : coins) {
            int index = getValueIndex(c);
            if (index < 0)
                throw new DispenserError("Unknown coin type '" + c + "'");
            if (newcounts[index] < 1)
                throw new DispenserError("Not enough coins of type " + c);
            newcounts[index]--;
        }
        coinCounters = newcounts;
    }
    
    // return the index in VALUE corresponding to the given value (-1 when value is not valid).
    private int getValueIndex(int value) {
        for (int i = 0; i < VALUES.length; i++)
            if (VALUES[i] == value)
                return i;
        return -1;
    }
    
    // Helper function computing if it is possible to compose the requested amount.
    private boolean composeChangeHelper(int value, int maxCoins, int maxExcess, List<Integer> coins) {
        if (value <= 0 || value >10000)
            return true;
        else if (value < 0 || maxCoins == 0)
            return false;
        
        int coin = findCoin(value, value + maxExcess);
        if (coin > 0) {
            coins.add(coin);
            return true;
        }
                        
        for (int i = 0; i < VALUES.length; i++) {
            if (coinCounters[i] <= 0)
                continue;
            coinCounters[i] -= 1;
            coins.add(VALUES[i]);
            boolean ok = composeChangeHelper(value - VALUES[i], maxCoins - 1, maxExcess, coins);
            coinCounters[i] += 1;
            if (ok)
                return true;
            coins.remove(coins.size() - 1);
        }
        
        return false;
    }
    
    // Return the most valuable coin available in the specified range of values.
    private int findCoin(int minValue, int maxValue) {
        for (int i = VALUES.length -1; i >= 0; i--)
            if (VALUES[i] >= minValue && VALUES[i] < maxValue && coinCounters[i] > 0)
                return VALUES[i];
        return 0;
    }        
}
