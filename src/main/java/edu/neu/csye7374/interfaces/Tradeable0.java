package edu.neu.csye7374.interfaces;

public interface Tradeable0 extends Tradable{
	
	default void setBid(double bid) {
		double newprice = bid + ( 0.5 * bid);
	}
	
	default int getMetric() {
		return 0;	
	}
}
