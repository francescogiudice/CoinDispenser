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
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test of the CoinDispenser class.
 *  * 
 * @author Claudio Cusano <claudio.cusano@unipv.it>
 */
public class CoinDispenserTest 
{
    static Random r=new Random();
    static int n=r.nextInt(100);
    static CoinDispenser cd=new CoinDispenser(n);
    static int s=cd.countCoins(1)+cd.countCoins(2)+cd.countCoins(5)+cd.countCoins(10)+cd.countCoins(20)+cd.countCoins(50)+cd.countCoins(100)+cd.countCoins(200);
    
    
} 
   
