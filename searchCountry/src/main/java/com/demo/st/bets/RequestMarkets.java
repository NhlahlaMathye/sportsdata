package com.demo.st.bets;

public class RequestMarkets {

    private int market_id;
    private String name;

    @Override
    public String toString()
    {
        return "\n Name:" + name;
    }

    public int getMarket_id() {
        return market_id;
    }

    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
