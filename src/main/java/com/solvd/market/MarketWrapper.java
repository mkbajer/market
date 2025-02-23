package com.solvd.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.market.domain.MarketPlace;

import java.util.List;

@JsonPropertyOrder({"marketplaces"})
public class MarketWrapper {

    @JsonProperty("marketplaces")
    private List<MarketPlace> marketplaces;

    public List<MarketPlace> getMarketplaces() {
        return marketplaces;
    }

    public void setMarketplaces(List<MarketPlace> marketplaces) {
        this.marketplaces = marketplaces;
    }
}
