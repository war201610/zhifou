package edu.dwlx.entity;

import java.util.Date;

public class Comment {
    private int id;             //主键
    private int uid;            //评论者uid
    private String content;     //内容
    private int agree;          //点赞数
    private int toWho;          //向谁评论（用户的uid）（-1代表是对文章的评论，其余是对用户评论的评论）
    private Date createDate;    //评论日期

    public Comment(){
        agree = 0;
        createDate = new Date(System.currentTimeMillis());
    }

    public Comment(int uid, String content, int toWho){
        this();
        this.uid = uid;
        this.content = content;
        this.toWho = toWho;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public int getToWho() {
        return toWho;
    }

    public void setToWho(int toWho) {
        this.toWho = toWho;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }
}
