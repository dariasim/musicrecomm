package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class TasteDiveConfiguration {
    @Bean
    public TasteDiveApi TasteDiveClient() {
        final RestClient restClient = RestClient.builder().baseUrl("https://ws.audioscrobbler.com/2.0/").build();
        final RestClientAdapter adapter = RestClientAdapter.create(restClient);
        final HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(TasteDiveApi.class);
    }
}
