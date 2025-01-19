package edu.neu.csye7374.interfaces;

public interface Tradable1 extends Tradable{
	
	default int getMetric() {
		return 0;
	}

}
