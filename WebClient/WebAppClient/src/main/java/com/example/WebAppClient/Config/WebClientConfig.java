package com.example.WebAppClient.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getWebClient(WebClient.Builder webClientBuilder) {

        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
                // .baseUrl("https://card-service-cloudrun-lmgpq3qg3a-et.a.run.app/card-service/api")
                .baseUrl("http://localhost:8081/api")
                .build();
    }


    private HttpClient getHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10_000)
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(10))
                        .addHandlerLast(new WriteTimeoutHandler(10)));
    }
}
