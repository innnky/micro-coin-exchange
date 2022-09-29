package com.innky.coin.information.entity;

import com.innky.coin.match.dto.TradePlateDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author innnky
 * @date 2022/9/26 10:55
 */
@Data
public class MarketInformation {
	private List<String> symbols;
	private final ConcurrentHashMap<String, Kline> klines;
	private final ConcurrentHashMap<String, BigDecimal> marketPrices;
	private final ConcurrentHashMap<String, TradePlateDto> tradePlates;

	public MarketInformation() {
		this.klines = new ConcurrentHashMap<>();
		this.marketPrices = new ConcurrentHashMap<>();
		this.tradePlates = new ConcurrentHashMap<>();
	}



	public BigDecimal getMarketPrice(String symbol){
		return marketPrices.get(symbol);
	}

	public void setMarketPrice(String symbol, BigDecimal price){
		marketPrices.put(symbol, price);
	}

	public Kline getKline(String symbol, String level){
		String key = symbol + ":" + level;
		Kline kline = klines.get(key);
		if (kline ==null){
			kline = new Kline(symbol,level);
			klines.put(key, kline);
		}
		return kline;
	}

	public List<Kline> getSymbolAllKline(String symbol){
		return this.klines.entrySet().stream()
				.filter(stringKlineEntry -> stringKlineEntry.getKey().startsWith(symbol))
				.map(Map.Entry::getValue)
				.collect(Collectors.toList());
	}



}
