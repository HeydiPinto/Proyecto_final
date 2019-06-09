package com.example.proyecto_final;

public class singleVertical {
    private String tit2;
    private String desc2;
    private int image;
    public singleVertical(){

    }
    public singleVertical(String tit2,String desc2,int image){
        this.tit2=tit2;
        this.desc2=desc2;
        this.image=image;
    }
    public String getTit2(){return tit2;}
    public void setTit2(String tit2){this.tit2=tit2;}
    public String getDesc2(){return  desc2;}
    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
