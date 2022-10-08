package com.example.qgame.Models;

import com.example.qgame.helpers.Helper;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "blogs")
@Data
@Accessors(chain = true)
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "hibernate_sequences")
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String image;

    @CreatedDate
    private Date createdAt;

    private String createdBy;

    private Long likeCount;

    public String getCreatedAtString() {
        return Objects.requireNonNullElseGet(this.createdAt, Date::new).toLocaleString();
    }

    public String getContentNWords(int n) {
        return Helper.NWords(this.content, n);
    }

    public String getImageUrl() {
        return Helper.base_url() + "/images/blog/" + image;
    }

    public String getUrl() {
        return Helper.base_url() + "/blogs/" + Helper.slug(title) + "/" + id;
    }
}
