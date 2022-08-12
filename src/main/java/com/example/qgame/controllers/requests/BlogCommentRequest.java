package com.example.qgame.controllers.requests;

import com.example.qgame.Models.Blog;
import com.example.qgame.Models.BlogComment;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class BlogCommentRequest {
    @NonNull
    private String name;

    @NonNull
    private String email;

    @NonNull
    private String comment;

    @NonNull
    private Blog blog;

    public BlogComment toBlogComment(){
        BlogComment blogComment = new BlogComment();
        blogComment.setComment(comment);
        blogComment.setEmail(email);
        blogComment.setBlog(blog);
        blogComment.setName(name);
        return blogComment;
    }
}
