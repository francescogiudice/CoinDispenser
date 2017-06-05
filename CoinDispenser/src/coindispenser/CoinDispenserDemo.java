/*
 * Code used in the "Software Engineering" course.
 *
 * Copyright 2017 by Claudio Cusano (claudio.cusano@unipv.it)
 * Dept of Electrical, Computer and Biomedical Engineering,
 * University of Pavia.
 */
package coindispenser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class containing the main method.
 * 
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class CoinDispenserDemo {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CoinDispenser dispenser = new CoinDispenser(300);
        final int MAX_COINS = 10;
        final int MAX_EXTRA = 4;

        for (;;) {
            System.out.print("Enter the amount to dispense in cents: ");
            String line;            
            try {
                line = reader.readLine().trim();
            } catch (IOException ex) {
                System.err.println(ex);
                break;
            }
            if (line.length() == 0)
                break;
            int value = Integer.parseInt(line);
            List<Integer> coins = dispenser.composeChange(value, MAX_COINS, MAX_EXTRA);
            if (coins == null) {
                System.out.println("Impossible to dispense the coins.");
            } else {
                try {
                    dispenser.dispenseCoins(coins);
                    for (int c : coins)
                        System.out.print(c + " ");                
                    System.out.println("");
                } catch (DispenserError ex) {
                    Logger.getLogger(CoinDispenser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }    
}
