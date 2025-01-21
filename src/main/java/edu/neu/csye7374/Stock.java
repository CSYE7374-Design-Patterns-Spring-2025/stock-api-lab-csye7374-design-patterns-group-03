package edu.neu.csye7374;

import edu.neu.csye7374.interfaces.Tradable;

import java.util.ArrayList;
import java.util.List;

public class Stock implements Tradable {

	private String ID;
	
	private String description;
	protected double price;

	protected int metric;
	protected List<Double> bids;

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

	public List<Double> getBid(){
		return this.bids;
	}

	@Override
	public int getMetric() {
		return this.metric;
	}

	public String getMetricValue() {
		if (this.getMetric() < 0) {
			return "Low performance";
		} else if (this.getMetric() >= 0 && this.getMetric() <= 5) {
			return "Good performance";
		} else {
			return "Very Good performance";
		}
	}

	public void setMetric(int metric) {
		this.metric = Math.max(-10, Math.min(10, metric));
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

	public void calculatePrice() {
		double avgBid = getBid().stream().mapToDouble(Double::doubleValue).average().orElse(this.price);
		double priceChange = avgBid - this.price;
		if (priceChange > 0) setMetric(getMetric() + (int) (priceChange * 8));
		else setMetric(getMetric() - (int) (Math.abs(priceChange) * 8));
		setPrice(avgBid);
	}

}
