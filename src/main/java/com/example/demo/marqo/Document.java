package com.example.demo.marqo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Document {
    private String title;
    private String description;
    private String _id;

    @Override
    public String toString() {
        return "{\"Title\":\"%s\",\"Description\":\"%s\"".formatted(title, description)
                + (_id != null ? ",\"_id\":\"%s\"}".formatted(_id) : "}");
    }
}