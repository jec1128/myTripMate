package com.example.tripmate.Board;

import java.util.Date;

public class BoardModel {
    private String boardCode;
    private String nickname;
    private String destination;
    private int gender;
    private int minage;
    private int maxage;
    private String thema1;
    private String thema2;
    private String thema3;
    private String matchdate;

    public BoardModel(String boardCode,String nickname, String destination, int gender, int minage, int maxage, String thema1, String thema2, String thema3, String matchdate) {
        this.boardCode = boardCode;
        this.nickname = nickname;
        this.destination = destination;
        this.gender = gender;
        this.minage = minage;
        this.maxage = maxage;
        this.thema1 = thema1;
        this.thema2 = thema2;
        this.thema3 = thema3;
        this.matchdate = matchdate;
    }

    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMinage() {
        return minage;
    }

    public void setMinage(int minage) { this.minage = minage; }

    public int getMaxage() {
        return maxage;
    }

    public void setMaxage(int maxage) {
        this.maxage = maxage;
    }

    public String getThema1() {
        return thema1;
    }

    public void setThema1(String thema1) {
        this.thema1 = thema1;
    }

    public String getThema2() {
        return thema2;
    }

    public void setThema2(String thema2) {
        this.thema2 = thema2;
    }

    public String getThema3() {
        return thema3;
    }

    public void setThema3(String thema3) {
        this.thema3 = thema3;
    }

    public String getMatchdate() {
        return matchdate;
    }

    public void setMatchdate(String matchdate) {
        this.matchdate = matchdate;
    }
}
