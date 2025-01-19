package edu.neu.csye7374;

import edu.neu.csye7374.interfaces.Tradable;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Tradable {

	private String ID;
	
	private String description;
	protected double price;

	private int metric;
	
	private List<Double> bids;

	public Stock() {
		this.ID = "";
		this.price = 0;
		this.description = "";
		this.bids = new ArrayList<>();
		this.bids.add(price);
		this.metric = 0;
	}
	
	public Stock(String ID, double price, String description) {
		this.ID = ID;
		this.price = price;
		this.description = description;
		this.bids = new ArrayList<>();
		this.bids.add(price);
		this.metric = 0;
	}
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public void setBid(double bid) {
		this.bids.add(bid);	
		calculatePrice();
	}
	
	public void calculatePrice() {
		double avgBid = getBid().stream().mapToDouble(Double::doubleValue).average().orElse(this.price);
		double priceChange = avgBid - this.price;
		if (priceChange > 0) setMetric(getMetric() + (int) (priceChange * 8));
		else setMetric(getMetric() - (int) (Math.abs(priceChange) * 8));
		setPrice(avgBid);
	}
	
	public List<Double> getBid(){
		return this.bids;
	}

	@Override
	public int getMetric() {
		return this.metric;
	}
	
	public String getMetricValue() {
		if(this.getMetric() < -3) {
            return " Metric value is too low";
        } else if (this.getMetric() >= -3 && this.getMetric() <= -1) {
            return "Metric value is good";
        } else {
        	return "Metric value is excellent";
        }
	}

	public void setMetric(int metric) {
		this.metric = metric;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"ID='" + ID + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", metric=" + (metric + " | " + getMetricValue()) +
				'}';
	}



	public void setDescription(String desc) {
		this.description = desc;
	}
}
