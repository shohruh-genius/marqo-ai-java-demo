package com.example.demo.marqo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonInclude(Include.NON_NULL)
public class Document {
    private String title;
    private String description;
    private String id;

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }
}