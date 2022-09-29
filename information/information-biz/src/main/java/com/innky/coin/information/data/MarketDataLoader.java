package com.innky.coin.information.data;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.exchange.dto.MarketDto;
import com.innky.coin.exchange.feign.RemoteMarketService;
import com.innky.coin.information.entity.MarketInformation;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author innnky
 * @date 2022/9/26 10:56
 */
@Component
@AllArgsConstructor
public class MarketDataLoader implements CommandLineRunner {

	private final MarketInformation marketInformation;
	private final RemoteMarketService remoteMarketService;


	@Override
	public void run(String... args) throws Exception {
		R<List<MarketDto>> allMarkets = remoteMarketService.getAllMarkets(SecurityConstants.FROM_IN);
		List<String> collect = allMarkets.getData().stream().map(MarketDto::getSymbol).collect(Collectors.toList());
		marketInformation.setSymbols(collect);
	}
}
