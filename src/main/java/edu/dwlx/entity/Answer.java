package edu.dwlx.entity;

import java.util.Date;

public class Answer {
    private int id;             //主键
    private int uid;            //回答者uid
    private int questionId;     //对应问题的id
    private String content;     //内容
    private int agree;          //点赞数量
    private String comment;     //评论表的名字
    private int collection;     //收藏数量
    private Date createDate;    //回答时间

    public Answer(){
        comment = "default";
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

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", uid=" + uid +
                ", questionId=" + questionId +
                ", content='" + content + '\'' +
                ", agree=" + agree +
                ", comment='" + comment + '\'' +
                ", collection=" + collection +
                ", createDate=" + createDate +
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
