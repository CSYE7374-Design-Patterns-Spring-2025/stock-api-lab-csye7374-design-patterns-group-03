package edu.neu.csye7374.stocks;

import edu.neu.csye7374.Stock;

public class CvsStock extends Stock  {
    public CvsStock() {
        super();
    }

    public CvsStock(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    public void calculatePrice() {
        double avgBid = getBid().stream().mapToDouble(Double::doubleValue).average().orElse(this.price);
        double priceChange = avgBid - this.price;
        if (priceChange > 0) setMetric(getMetric() + (int) (priceChange * 0.3));
        else setMetric(getMetric() - (int) (Math.abs(priceChange) * 0.3));
        setPrice(avgBid);
    }
}
