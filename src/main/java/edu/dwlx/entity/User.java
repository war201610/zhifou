package edu.dwlx.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;

public class User implements UserDetails
{
    private int uid;
    private String name;
    private String password;
    private String gender;
    private String career;
    private String introduction;
    private String address;
    private Date registerDate;
    private String email;
    private String following;
    private String follower;
    private String collect_article;
    private String collect_answer;
    private String question;
    private String answer;
    private int like_count;

    public User(){
        this.registerDate = new Date(System.currentTimeMillis());
        this.following = "default";
        this.follower = "default";
        this.collect_article = "default";
        this.collect_answer = "default";
        this.question = "default";
        this.answer = "default";
        this.like_count = 0;
    }

    public User(String username, String password) {
        this();
        this.name = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", career='" + career + '\'' +
                ", introduction='" + introduction + '\'' +
                ", address='" + address + '\'' +
                ", registerDate=" + registerDate +
                ", email='" + email + '\'' +
                ", following='" + following + '\'' +
                ", follower='" + follower + '\'' +
                ", collect_article='" + collect_article + '\'' +
                ", collect_answer='" + collect_answer + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", like_count=" + like_count +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
        this.following = uid+"_following";
        this.follower = uid+"_follower";
        this.collect_article = uid+"_collect_article";
        this.collect_answer = uid+"_collect_answer";
        this.question = uid+"_question";
        this.answer = uid+"_answer";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getCollect_article() {
        return collect_article;
    }

    public void setCollect_article(String collect_article) {
        this.collect_article = collect_article;
    }

    public String getCollect_answer() {
        return collect_answer;
    }

    public void setCollect_answer(String collect_answer) {
        this.collect_answer = collect_answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public void setRegisterTime(java.sql.Date date) {
        this.registerDate = date;
    }
}
