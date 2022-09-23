package com.innky.coin.exchange.feign;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.constant.ServiceNameConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.exchange.dto.MarketDto;
import com.innky.coin.exchange.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @author innnky
 * @date 2022/9/23 11:26
 */
@FeignClient(contextId = "remoteMarketService", value = ServiceNameConstants.EXCHANGE_SERVICE)
public interface RemoteMarketService {

	/**
	 * 得到所有市场
	 *
	 * @param from 从
	 * @return {@link R}<{@link List}<{@link MarketDto}>>
	 */
	@GetMapping("/market/all")
	R<List<MarketDto>> getAllMarkets(@RequestHeader(SecurityConstants.FROM) String from);

}
