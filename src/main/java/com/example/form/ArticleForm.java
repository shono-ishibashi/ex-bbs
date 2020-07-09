package com.example.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class ArticleForm {
    @Size(min = 0,max =50 ,message = "名前は50文字以内で入力してください")
    @NotBlank(message = "名前を入力してください")
    private String name;

    @Size(min = 0,max =140 ,message = "記事は140文字以内で入力してください")
    @NotBlank(message = "記事を入力してください")
    private String content;


    //getter setter

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
}
