package edu.dwlx.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;

public class User implements UserDetails
{
    private int uid;                    //主键
    private String name;                //用户名
    private String password;            //密码
    private String nickname;            //昵称
    private int gender;                 //性别
    private String career;              //职业
    private String introduction;        //个人简介
    private String address;             //地址
    private Date registerDate;          //注册日期
    private String email;               //邮箱
    private String following;           //关注了谁的表名
    private String follower;            //谁关注了我的表名
    private String collect_article;     //收藏的文章的表名
    private String collect_answer;      //收藏的问题的表名
    private String answer;              //做出的回答的表名
    private int like_count;             //获得的点赞数量

    public User(){
        this.registerDate = new Date(System.currentTimeMillis());
        this.nickname = name;
        this.following = "default";
        this.follower = "default";
        this.collect_article = "default";
        this.collect_answer = "default";
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
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", career='" + career + '\'' +
                ", introduction='" + introduction + '\'' +
                ", address='" + address + '\'' +
                ", registerDate=" + registerDate +
                ", email='" + email + '\'' +
                ", following='" + following + '\'' +
                ", follower='" + follower + '\'' +
                ", collect_article='" + collect_article + '\'' +
                ", collect_answer='" + collect_answer + '\'' +
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public void setRegisterDate(java.sql.Date date) {
        this.registerDate = date;
    }
    
    
}
