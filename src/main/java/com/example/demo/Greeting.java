package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;

public class Greeting {
    private long id;
    private String content;
   // private static int id = 0;



    public Greeting() {
        this.id = 0;
        this.content = "";
    }

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() { return id; }

    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }
}
