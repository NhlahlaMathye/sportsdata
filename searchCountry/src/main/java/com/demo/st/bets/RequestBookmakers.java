package com.demo.st.bets;

import java.io.File;

public class RequestBookmakers {

    private int bookmaker_id;
    private String name;
    private File img;

    @Override
    public String toString()
    {
        return "\n Name: " + name + " Image: " + img;
    }

    public int getBookmaker_id() {
        return bookmaker_id;
    }

    public void setBookmaker_id(int bookmaker_id) {
        this.bookmaker_id = bookmaker_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }
}
