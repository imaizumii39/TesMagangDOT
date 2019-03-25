package com.example.alex.testapp;

/**
 * Created by Alex on 3/25/2019.
 */

public class CommentData {
    private String name,email, body;
    int id_post;

    public CommentData(String name, String email, String body, int id_post) {
        this.name = name;
        this.email = email;
        this.body = body;
        this.id_post = id_post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }
    public String toString(){
        return String.format("{name: %s, email: %s, body: %s, id_post: %d}", name, email, body, id_post);
    }
}
