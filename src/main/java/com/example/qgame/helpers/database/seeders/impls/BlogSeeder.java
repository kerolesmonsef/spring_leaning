package com.example.qgame.helpers.database.seeders.impls;

import com.example.qgame.Models.Blog;
import com.example.qgame.QGameApplication;
import com.example.qgame.helpers.database.seeders.ISeeder;
import com.example.qgame.repositories.BlogRepository;
import com.example.qgame.repositories.CategoryRepository;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlogSeeder extends ISeeder<Blog> {

    public static void main(String[] args) {

    }

    @Override
    protected Collection<Blog> data() {
        List<Blog> blogs = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            Blog blog = new Blog();
            blog.setImage(i + ".jpg");
            blog.setTitle(faker().lorem().sentence(4));
            blog.setContent(faker().lorem().sentence(500));
            blog.setLikeCount(faker().random().nextInt(12, 432).longValue());
            blog.setCreatedBy(faker().name().name());

            blogs.add(blog);
        }

        return blogs;
    }

    @Override
    public void seed() {
        BlogRepository blogRepository = QGameApplication.getBean(BlogRepository.class);
        blogRepository.saveAll(data());
    }
}
