package com.example.alex.testapp;

/**
 * Created by Alex on 3/25/2019.
 */

class PostData {
    private String title,body;
    int id;

    public PostData(String title, String body, int id) {
        this.title = title;
        this.body = body;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
