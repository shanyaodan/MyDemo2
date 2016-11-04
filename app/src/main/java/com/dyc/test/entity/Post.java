package com.dyc.test.entity;

import com.dyc.test.base.BaseEntity;

import java.io.Serializable;

public class Post implements Serializable{
    public String id;

    public String type;

    public String slug;

    public String url;

    public String status;

    public String title;

    public String title_plain;

    public String content;

    public String excerpt;

    public String date;

    public String modified;

//    public Author author;

    public String comment_count;

    public String comment_status;

    public static class Author {
        public int id;

        public String slug;

        public String name;

        public String first_name;

        public String last_name;

        public String nickname;

        public String url;

        public String description;
    }
}


