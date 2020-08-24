package edu.dwlx.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

public class User
        implements UserDetails
{
    private int uid;
    private String name;
    private int gender;
    private String password;
    private String career;
    private String introduction;
    private String address;
    private String industry;
    private String collection;
    private Date registerTime;
    private String email;
    private String question;
    private String answer;
    private String subscribeQuestion;
    private String subscribeMember;
    private String followMember;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", career='" + career + '\'' +
                ", introduction='" + introduction + '\'' +
                ", address='" + address + '\'' +
                ", industry='" + industry + '\'' +
                ", collection='" + collection + '\'' +
                ", registerTime=" + registerTime +
                ", email='" + email + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", subscribeQuestion='" + subscribeQuestion + '\'' +
                ", subscribeMember='" + subscribeMember + '\'' +
                ", followMember='" + followMember + '\'' +
                '}';
    }

    public User() {
    }

    public User(int uid, String name, int gender, String password, String career, String introduction, String address, String industry, String collection, Date registerTime, String email, String question, String answer, String subscribeQuestion, String subscribeMember, String followMember) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.password = password;
        this.career = career;
        this.introduction = introduction;
        this.address = address;
        this.industry = industry;
        this.collection = collection;
        this.registerTime = registerTime;
        this.email = email;
        this.question = question;
        this.answer = answer;
        this.subscribeQuestion = subscribeQuestion;
        this.subscribeMember = subscribeMember;
        this.followMember = followMember;
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getSubscribeQuestion() {
        return subscribeQuestion;
    }

    public void setSubscribeQuestion(String subscribeQuestion) {
        this.subscribeQuestion = subscribeQuestion;
    }

    public String getSubscribeMember() {
        return subscribeMember;
    }

    public void setSubscribeMember(String subscribeMember) {
        this.subscribeMember = subscribeMember;
    }

    public String getFollowMember() {
        return followMember;
    }

    public void setFollowMember(String followMember) {
        this.followMember = followMember;
    }
}
