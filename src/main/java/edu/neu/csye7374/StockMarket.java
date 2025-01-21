package edu.neu.csye7374;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neu.csye7374.interfaces.Tradable;
import edu.neu.csye7374.stocks.*;

public class StockMarket {
	
	private static volatile StockMarket instance;
	private final Map<String, Tradable> stocks;
	private StockMarket() {
		stocks = new HashMap<>();
	}

    public static StockMarket getInstance() {
        if (instance == null) {
            synchronized (StockMarket.class) {
                if (instance == null) {
                    instance = new StockMarket();
                }
            }
        }
        return instance;
    }

	public void addStock(Tradable stock) {
		stocks.put(stock.getID(), stock);
	}

	public void showAllStocks() {
		stocks.values().forEach(System.out::println);
		System.out.println("-----------------------------------------------------------------------------------------|\n");
	}

	public void tradeStocks(String name, double bid) {
		Tradable stock = stocks.get(name);
		if (stock != null) {
			stock.setBid(bid);
		}
	}

	public void removeStock(String name) {
		if (stocks.remove(name) != null) {
			System.out.println("Removed stock: " + name);
		} else {
			System.out.println("Stock not found: " + name);
		}
	}

	public void tradeMultipleStocks(Map<String, Double> bids) {
		for (Map.Entry<String, Double> entry : bids.entrySet()) {
			tradeStocks(entry.getKey(), entry.getValue());
		}
	}
    
	public void demo() {

		System.out.println("[Trading Started]\n");
		addStock(new BoseStock("Bose", 25.3, "Bose Corporation"));
		addStock(new CvsStock("CVS Health", 22.16, "CVS Health Corporation"));
		addStock(new MetaStock("Meta", 15, "Meta Platforms Inc"));
		addStock(new BmoStock("BMO", 24.65, "Bank Of Montreal"));
		addStock(new StapleStock("Staples", 21.65, "Staples Inc"));

		System.out.println("Status of the Stocks before bidding.");
		showAllStocks();

		tradeMultipleStocks(Map.of(
				"Bose", 21.0,
				"CVS Health", 24.0,
				"Meta", 23.0,
				"BMO", 32.0,
				"Staples", 25.45
		));
		System.out.println("Status after bid 1:");
		showAllStocks();

		tradeMultipleStocks(Map.of(
				"Bose", 26.0,
				"CVS Health", 21.0,
				"Meta", 19.0,
				"BMO", 29.0,
				"Staples", 21.40
		));
		System.out.println("Status after bid 2:");
		showAllStocks();

		tradeMultipleStocks(Map.of(
				"Bose", 24.0,
				"CVS Health", 28.0,
				"Meta", 10.0,
				"BMO", 35.0,
				"Staples", 15.45
		));
		System.out.println("Status after bid 3:");
		showAllStocks();

		tradeMultipleStocks(Map.of(
				"Bose", 23.6,
				"CVS Health", 25.74,
				"Meta", 13.0,
				"BMO", 30.0,
				"Staples", 22.15
		));
		System.out.println("Status after bid 4:");
		showAllStocks();

		tradeMultipleStocks(Map.of(
				"Bose", 27.45,
				"CVS Health", 10.65,
				"Meta", 16.0,
				"BMO", 25.0,
				"Staples", 24.75
		));
		System.out.println("Status after bid 5:");
		showAllStocks();

		tradeMultipleStocks(Map.of(
				"Bose", 20.0,
				"CVS Health", 53.16,
				"Meta", 5.0,
				"BMO", 36.0,
				"Staples", 27.47
		));
		System.out.println("Status after bid 6:");
		showAllStocks();


		removeStock("Bose");
		System.out.println("Status of the Stocks after removing a stock");
		showAllStocks();

		System.out.println("[Trading Ended]\n");
	}
}	