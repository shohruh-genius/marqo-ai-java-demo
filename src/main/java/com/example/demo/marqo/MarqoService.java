package com.example.demo.marqo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MarqoService {
    @Autowired
    private WebClient webClient;

    public Object createIndex(String indexName) {
        return webClient.post().uri("/indexes/%s".formatted(indexName))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(Object.class).block();
    }

    public Object addDocuments(String indexName, List<Document> documents) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return webClient.post()
                    .uri("/indexes/%s/documents".formatted(indexName))
                    .headers(header -> header.setBasicAuth("admin", "admin"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(mapper.writeValueAsString(documents))
                    .retrieve().bodyToMono(Object.class).block();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object search(String indexName, String query) {
        return webClient.post().uri("/indexes/%s/search".formatted(indexName))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"q\":\"%s\"}".formatted(query))
                .retrieve().bodyToMono(Object.class).block();
    }
}