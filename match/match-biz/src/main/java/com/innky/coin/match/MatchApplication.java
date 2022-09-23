package com.innky.coin.match;

import com.innky.coin.common.feign.annotation.EnableCoinFeignClients;
import com.innky.coin.common.security.annotation.EnableCoinResourceServer;
import com.innky.coin.common.swagger.annotation.EnableCoinDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* @author pig archetype
* <p>
* 项目启动类
*/
@EnableCoinDoc
@EnableCoinFeignClients
@EnableCoinResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class MatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchApplication.class, args);
    }

}
