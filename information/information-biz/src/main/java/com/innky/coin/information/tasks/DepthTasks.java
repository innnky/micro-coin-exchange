package com.innky.coin.information.tasks;

import com.innky.coin.common.core.constant.CacheConstants;
import com.innky.coin.common.core.constant.SecurityConstants;
import com.innky.coin.common.core.util.R;
import com.innky.coin.information.entity.MarketInformation;
import com.innky.coin.match.dto.TradePlateDto;
import com.innky.coin.match.feign.RemoteMatchService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author innnky
 * @date 2022/9/26 17:55
 */
@Component
@AllArgsConstructor
public class DepthTasks {
	private final RedisTemplate<String, Object> redisTemplate;
	private final RemoteMatchService remoteMatchService;

	@Scheduled(fixedRate = 1000)
	public void updateDepth(){
		R<Map<String, TradePlateDto>> allDepth = remoteMatchService.getAllDepth(SecurityConstants.FROM_IN);
		allDepth.getData().forEach(((symbol, tradePlateDto) -> {
			redisTemplate.opsForValue().set(CacheConstants.DEPTH_KEY + symbol, tradePlateDto);
		}));
	}

}
