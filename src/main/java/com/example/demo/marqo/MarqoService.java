package com.example.demo.marqo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MarqoService {
    @Autowired
    private WebClient webClient;

    public String createIndex(String indexName) {
        return webClient.post().uri("/indexes/%s".formatted(indexName))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(String.class).block();
    }

    public Object addDocuments(String indexName, List<Document> documents) {
        String body = documents.stream().map(Document::toString).collect(Collectors.joining(",", "[", "]"));
        return webClient.post()
                .uri("/indexes/%s/documents".formatted(indexName))
                .headers(header -> header.setBasicAuth("admin", "admin"))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve().bodyToMono(Object.class).block();
    }

    public Object search(String indexName, String query) {
        return webClient.post().uri("/indexes/%s/search".formatted(indexName))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"q\":\"%s\"}".formatted(query))
                .retrieve().bodyToMono(Object.class).block();
    }
}