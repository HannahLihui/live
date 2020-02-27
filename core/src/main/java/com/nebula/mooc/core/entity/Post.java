package com.nebula.mooc.core.entity;


import java.util.Date;

public class Post {

    private long id;
    private long userId;
    private String nickName;
    private String kindName;
    private String title;
    private String content;
    private String headimg;
    private Date createdTime;
    private Date ifLike;
    private int like;
    private Date ifStar;
    private int star;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public Date getIfLike() {
        return ifLike;
    }

    public void setIfLike(Date ifLike) {
        this.ifLike = ifLike;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public Date getIfStar() {
        return ifStar;
    }

    public void setIfStar(Date ifStar) {
        this.ifStar = ifStar;
    }
}
