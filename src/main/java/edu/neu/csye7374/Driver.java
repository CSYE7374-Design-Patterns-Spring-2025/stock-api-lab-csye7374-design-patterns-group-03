package edu.neu.csye7374;

/**
 * 
 * @author Preyash Mehta
 * 
 */

public class Driver {
    public static void main(String[] args) {
        System.out.println("============Main Execution Start===================\n\n");

        StockMarket.getInstance().startTrading();

        System.out.println("============Main Execution End===================");
   
    }
}



