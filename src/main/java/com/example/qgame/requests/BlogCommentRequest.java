package com.example.qgame.requests;

import com.example.qgame.Models.BlogComment;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;


@Data
@Accessors(chain = true)
public class BlogCommentRequest {

    @NotEmpty
    @Size(min = 5, message = "name must greater than 5 chars")
    private String name;

    @NotEmpty
    @Email
    private String email;

    @Size(min = 5, message = "blog comment must greater than 5 chars")
    @NotNull
    private String comment;

    public BlogComment toBlogComment() {

        return new BlogComment()
                .setComment(comment)
                .setEmail(email)
                .setName(name);

    }
}
