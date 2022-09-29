package com.innky.coin.information;

import com.innky.coin.common.feign.annotation.EnableCoinFeignClients;
import com.innky.coin.common.security.annotation.EnableCoinResourceServer;
import com.innky.coin.common.swagger.annotation.EnableCoinDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

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
@EnableScheduling
public class InformationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InformationApplication.class, args);
    }

}
