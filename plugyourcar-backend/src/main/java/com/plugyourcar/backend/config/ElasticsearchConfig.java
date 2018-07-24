package com.plugyourcar.backend.config;
import java.net.InetSocketAddress;
import javax.annotation.Resource;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


// Clase para configurar la conexión con el servidor elasticsearch
@Configuration
@PropertySource(value = "classpath:application.properties")
@EnableElasticsearchRepositories(basePackages = "com.plugyourcar.backend.repository.elasticsearch")
public class ElasticsearchConfig {

    @Resource
    private Environment environment;

    @Bean
    public Client client() {
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY);
        TransportAddress transportAddress= new InetSocketTransportAddress(new InetSocketAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port"))));
        client.addTransportAddress(transportAddress);
        return client;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchTemplate(client());
    }
}