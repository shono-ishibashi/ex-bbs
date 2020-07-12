package com.example.domain;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String name;
    private String content;
    private String articleId;
}
