package edu.neu.csye7374.stocks;

import java.util.List;

import edu.neu.csye7374.Stock;

public class BmoStock extends Stock {
    public BmoStock() {
        super();
    }

    public BmoStock(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    public void calculatePrice() {
        List<Double> bids = getBid();
        if (bids.isEmpty()) return;

        double avgBid = bids.stream().mapToDouble(Double::doubleValue).average().orElse(this.price);
        double priceChange = avgBid - this.price;

        setPrice(avgBid);

        int metricChange = (int) Math.round(priceChange * 2);
        setMetric(getMetric() + metricChange);
    }
}
