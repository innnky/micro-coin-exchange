package com.innky.coin.exchange.feign;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.constant.ServiceNameConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.exchange.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @author innnky
 * @date 2022/9/23 10:02
 */
@FeignClient(contextId = "remoteOrderService", value = ServiceNameConstants.EXCHANGE_SERVICE)
public interface RemoteOrderService {

	/**
	 * 得到所有未成交订单
	 *
	 * @param from 从
	 * @return {@link R}<{@link List}<{@link OrderDto}>>
	 */
	@GetMapping("/order/open")
	R<List<OrderDto>> getOpenOrders(@RequestHeader(SecurityConstants.FROM) String from);

}
