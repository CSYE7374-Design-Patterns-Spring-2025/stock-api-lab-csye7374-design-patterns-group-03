package edu.neu.csye7374;
import java.util.ArrayList;
import java.util.List;

import edu.neu.csye7374.interfaces.Tradable;
import edu.neu.csye7374.stocks.BoseStock;
import edu.neu.csye7374.stocks.CvsStock;

public class StockMarket {
	
	private static volatile StockMarket instance;
    private final List<Tradable> stocks;

    private StockMarket() {
        stocks = new ArrayList<>();
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
        stocks.add(stock);
    }
    
    public void showAllStocks() {
        for (Tradable stock : stocks) {
            System.out.println(stock);
        }
		System.out.println("-----------------------------------------------------------------------------------------|\n");;
    }
    
    public void tradeStocks(String name, double bid) {
        for (Tradable stock : stocks) {
            if(stock.getID().equals(name))
            	stock.setBid(bid);
        }
    }
    
    public void removeStock(String name) {
    	int index = -1;
    	for (int i=0;i < stocks.size();i++) {
            if(stocks.get(i).getID().equals(name))
            	index = i;
        }
    	if(index!=-1)
    		stocks.remove(index);
    }   
    
	public void startTrading() {

		System.out.println("[Trading Started]\n");
		Stock bose = new BoseStock();
		bose.setPrice(25.3);
		bose.setID("Bose");
		bose.setDescription("Bose");
		this.addStock(bose);
	
		Stock cvsStock = new CvsStock();
		cvsStock.setPrice(22.16);
		cvsStock.setID("CVS Health");
		cvsStock.setDescription("CVS Health");
		this.addStock(cvsStock);


		System.out.println("Status of the Stocks before bidding.");
		showAllStocks();

		tradeStocks("Bose", 21);
		tradeStocks("CVS Health", 24);
		System.out.println("Status of the Stocks after bid 1: ");
		showAllStocks();
	
		tradeStocks("Bose", 26);
		tradeStocks("CVS Health", 21);
		System.out.println("Status of the Stocks after bid 2: ");
		showAllStocks();
	
		tradeStocks("Bose", 24);
		tradeStocks("CVS Health", 28);
		System.out.println("Status of the Stocks after bid 3: ");
		showAllStocks();
	
		tradeStocks("Bose", 23);
		tradeStocks("CVS Health", 25);
		System.out.println("Status of the Stocks after bid 4: ");
		showAllStocks();
	
		tradeStocks("Bose", 27);
		tradeStocks("CVS Health", 10);
		System.out.println("Status of the Stocks after bid 5: ");
		showAllStocks();
	
		tradeStocks("Bose", 25);
		tradeStocks("CVS Health", 23);
		System.out.println("Status of the Stocks after bid 6: ");
		showAllStocks();
	
		System.out.println("Status of the Stocks after all bidding.");
		showAllStocks();
	
		removeStock("Bose");
		System.out.println("Status of the Stocks after removing a stock");
		showAllStocks();

		System.out.println("[Trading Ended]\n");
	}
}	