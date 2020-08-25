package edu.dwlx.entity;

import java.util.Date;

public class Question {
    private int id;
    private int uid;
    private String content;
    private String introduction;
    private String tag;
    private int agree_count;
    private String comment;
    private String answer;
    private String follower;
    private int viewCount;
    private int collectCount;
    private Date date;

    public Question(){
        agree_count = 0;
        comment = "default";
        answer = "default";
        follower = "default";
        viewCount = 0;
        collectCount = 0;
        date = new Date(System.currentTimeMillis());
    }

    public Question(int uid, String content){
        this();
        this.uid = uid;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        comment = id + "_question_comment";
        answer = id + "_question_answer";
        follower = id + "_question_follower";
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getAgree_count() {
        return agree_count;
    }

    public void setAgree_count(int agree_count) {
        this.agree_count = agree_count;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
