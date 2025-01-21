package edu.neu.csye7374.stocks;

import edu.neu.csye7374.Stock;

public class BoseStock extends Stock {
    public BoseStock() {
        super();
    }

    public BoseStock(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    public void calculatePrice() {
        if (bids.isEmpty()) return;

        double avgBid = bids.stream().mapToDouble(Double::doubleValue).average().orElse(this.price);
        double priceChange = avgBid - this.price;

        setPrice(avgBid);

        int metricChange = (int) Math.round(priceChange * 8);
        setMetric(getMetric() + metricChange);
    }
}
