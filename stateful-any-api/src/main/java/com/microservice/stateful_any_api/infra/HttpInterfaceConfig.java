package com.microservice.stateful_any_api.infra;

import com.microservice.stateful_any_api.core.cliente.TokenClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {

    @Value("${app.client.base-url}")
    private String baseUrl;

    @Bean
    public TokenClient tokenClient() {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        WebClientAdapter adapter = WebClientAdapter.create(webClient);

        return HttpServiceProxyFactory.builderFor(adapter).build().createClient(TokenClient.class);
    }
}
