package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentForm {

    @NotBlank(message = "名前を入力してください")
    @Size(min = 1,max =50 ,message = "名前は50文字以内で入力してください")
    private String name;

    @NotBlank(message = "コメントを入力してください")
    @Size(min = 1,max =140 ,message = "コメントは140文字以内で入力してください")
    private String content;


    private String articleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
