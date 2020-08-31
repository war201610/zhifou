package edu.dwlx.entity;

import java.util.Date;

public class Article {
    private int id;             //主键
    private int uid;            //文章的发布者uid
    private String text;        //内容
    private int agree;          //点赞数
    private String comment;     //评论的表名
    private Date createDate;    //发布日期
    private String tag;         //标签

    public Article(){
        createDate = new java.sql.Date(System.currentTimeMillis());
        comment = "default";
    }

    public Article(int uid, String text) {
        this();
        this.uid = uid;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", uid=" + uid +
                ", text='" + text + '\'' +
                ", agree=" + agree +
                ", comment='" + comment + '\'' +
                ", createDate=" + createDate +
                ", tag='" + tag + '\'' +
                '}';
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
