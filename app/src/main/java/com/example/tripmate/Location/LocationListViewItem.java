package com.example.tripmate.Location;

public class LocationListViewItem {
    private String item_title;
    private String item_address1;
    private String item_firstimage; //썸네일 이미지
    private String item_tel;
    //private int item_dist;

    public String getTitle(){
        return item_title;
    }
    public String getAddress1(){
        return item_address1;
    }
    public String getFirstimage(){
        return item_firstimage;
    }
    public String getTel(){
        return item_tel;
    }

    public void setTitle(String item_title){
        this.item_title = item_title;
    }
    public void setFirstimage(String item_firstimage){
        this.item_firstimage =item_firstimage;
    }
    public void setAddress1(String item_address1){
        this.item_address1 = item_address1;
    }
    public void setTel(String item_tel){
        this.item_tel = item_tel;
    }

}
