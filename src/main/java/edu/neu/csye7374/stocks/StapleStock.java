package edu.neu.csye7374.stocks;

import edu.neu.csye7374.Stock;

public class StapleStock extends Stock {
    public StapleStock() {
        super();
    }

    public StapleStock(String ID, double price, String description) {
        super(ID, price, description);
    }

    @Override
    public void calculatePrice() {
        if (bids.isEmpty()) return;
        double avgBid = bids.stream().mapToDouble(Double::doubleValue).average().orElse(this.price);
        double priceChange = avgBid - this.price;
        if (priceChange > 0)
            setMetric(getMetric() + (int) (priceChange * 4));
        else
            setMetric(getMetric() - (int) (Math.abs(priceChange) * 4));
        setPrice(avgBid);
    }
}
