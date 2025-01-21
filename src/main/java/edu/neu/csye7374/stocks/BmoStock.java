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
        double weightSum = 0;
        double weightedBidSum = 0;
        for (int i = 0; i < bids.size(); i++) {
            double weight = i + 1; // Weight increases with newer bids
            weightedBidSum += bids.get(i) * weight;
            weightSum += weight;
        }
        double weightedAvgBid = weightSum > 0 ? weightedBidSum / weightSum : this.price;

        // Calculate price change and update the metric accordingly
        double priceChange = weightedAvgBid - this.price;
        double metricAdjustment = Math.log1p(Math.abs(priceChange)) * (priceChange > 0 ? 1 : -1);

        setMetric(getMetric() + (int) (metricAdjustment * 5)); // Adjust metric with a scaling factor
        setPrice(weightedAvgBid);
    }
}
