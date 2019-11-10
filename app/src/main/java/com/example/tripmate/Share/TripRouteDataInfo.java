package com.example.tripmate.Share;

import java.util.ArrayList;

public class TripRouteDataInfo {

    String tripTitle;
    String tripLocation;
    String tripDescribe;
    String titleImgURL;
    ArrayList<String> tripRouteList;

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getTripLocation() {
        return tripLocation;
    }

    public void setTripLocation(String tripLocation) {
        this.tripLocation = tripLocation;
    }

    public String getTripDescribe() {
        return tripDescribe;
    }

    public void setTripDescribe(String tripDescribe) {
        this.tripDescribe = tripDescribe;
    }

    public String getTitleImgURL() {
        return titleImgURL;
    }

    public void setTitleImgURL(String titleImgURL) {
        this.titleImgURL = titleImgURL;
    }

    public ArrayList<String> getTripRouteList() {
        return tripRouteList;
    }

    public void setTripRouteList(ArrayList<String> tripRouteList) {
        this.tripRouteList = tripRouteList;
    }

}
