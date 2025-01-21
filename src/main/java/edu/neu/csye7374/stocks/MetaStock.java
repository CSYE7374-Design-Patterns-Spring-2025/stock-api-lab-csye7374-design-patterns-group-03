package edu.neu.csye7374.stocks;

import edu.neu.csye7374.Stock;

public class MetaStock extends Stock {
    public MetaStock() {
        super();
    }

    public MetaStock(String name, double price, String description) {
        super(name, price, description);
    }

    @Override
    public void calculatePrice() {
        double avg = 0;
        for (double p : getBid()) {
            avg += p;
        }
        avg = avg / getBid().size();
        if (avg > this.price && getMetric() >= 0) {
            setMetric(getMetric() + 1);
        } else if (avg > this.price && getMetric() < 0) {
            setMetric(0);
        } else if (avg < this.price && getMetric() <= 0) {
            setMetric(getMetric() - 1);
        } else if (avg < this.price && getMetric() > 0) {
            setMetric(0);
        }
        setPrice(avg);
    }
}
