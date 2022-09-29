package com.innky.coin.match.feign;

import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.constant.ServiceNameConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.match.dto.TradePlateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

/**
 * @author innnky
 * @date 2022/9/24 20:48
 */
@FeignClient(contextId = "remoteMatchService", value = ServiceNameConstants.MATCH_SERVICE)
public interface RemoteMatchService {

	/**
	 * 获取深度数据
	 *
	 * @param symbol 交易对符号
	 * @return {@link R}<{@link TradePlateDto}>
	 */
	@GetMapping("/depth/{symbol}")
	R<TradePlateDto> getDepth(@PathVariable("symbol") String symbol,@RequestHeader(SecurityConstants.FROM) String from);

	/**
	 * 得到所有深度数据
	 *
	 * @return {@link R}<{@link TradePlateDto}>
	 */
	@GetMapping("/depth/")
	R<Map<String, TradePlateDto>>  getAllDepth(@RequestHeader(SecurityConstants.FROM) String from);


}
