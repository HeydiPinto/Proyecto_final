package com.example.proyecto_final;

public class singleHorizontal {
    private int images;
    private String title;
    private String desc;

    public singleHorizontal(){

    }

    public singleHorizontal(int images,String title,String desc){
        this.images=images;
        this.title=title;
        this.desc=desc;
    }
    public String getDesc(){return desc;}
    public void setDesc(String desc){this.desc=desc;}


    public int getImages(){return  images;}
    public void setImages(int images){this.images=images;}
    public String getTitle(){return title;}

    public void setTitle(String title) {
        this.title = title;
    }
}
