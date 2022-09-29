package com.innky.coin.information.config;

import com.innky.coin.information.entity.MarketInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author innnky
 * @date 2022/9/26 10:55
 */
@Configuration
public class InformationConfig {

	@Bean
	public MarketInformation marketInformation(){
		return new MarketInformation();
	}

}
