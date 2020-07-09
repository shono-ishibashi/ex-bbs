package com.example.form;

import javax.validation.Valid;
import java.util.List;

public class ParentForm {

    @Valid
    private List<CommentForm> comments;

    public List<CommentForm> getComments() {
        return comments;
    }

    public void setComments(List<CommentForm> comments) {
        this.comments = comments;
    }
}
