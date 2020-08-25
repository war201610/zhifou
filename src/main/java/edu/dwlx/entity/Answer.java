package edu.dwlx.entity;

import java.util.Date;

public class Answer {
    private int id;
    private int uid;
    private int questionId;
    private String content;
    private int agree;
    private String comment;
    private int collection;
    private Date createDate;

    public Answer(){
        comment = id + "_answer_comment";
        agree = 0;
        collection = 0;
        createDate = new Date(System.currentTimeMillis());
    }

    public Answer(int uid, int questionId, String content) {
        this();
        this.uid = uid;
        this.questionId = questionId;
        this.content = content;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public Date getCreateDateDate() {
        return createDate;
    }

    public void setCreateDateDate(Date date) {
        this.createDate = date;
    }
}
