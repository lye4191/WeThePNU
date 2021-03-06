package com.example.wjdck.hakerton;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@IgnoreExtraProperties
public class ListViewItem implements Serializable {
    private String key;
    private String title;
    private String text;
    private String category;
    private long recommend;
    private String date;
    private int answerNum = 0;
    private String answerDate = "";

    public Map<String, Boolean> agree = new HashMap<>();
    public Map<String, Boolean> bookmark = new HashMap<>();
    public Map<String, Boolean> pushalarm = new HashMap<>();
    public Map<String, Boolean> clicked = new HashMap<>();

    public ListViewItem() {}

    public ListViewItem(String key, String title, String text, String category, long recommend, String date, int answerNum, String answerDate){
        this.key = key;
        this.title = title;
        this.text = text;
        this.category = category;
        this.recommend = recommend;
        this.date = date;
        this.answerNum = answerNum;
        this.answerDate = answerDate;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("key", key);
        result.put("title", title);
        result.put("text", text);
        result.put("category", category);
        result.put("recommend", recommend);
        result.put("date", date);
        result.put("answerNum", answerNum);
        result.put("answerDate", answerDate);
        result.put("agree", agree);
        result.put("bookmark", bookmark);
        result.put("pushalarm", pushalarm);
        result.put("clicked", clicked);
        return result;

    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public String getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(String answerDate) {
        this.answerDate = answerDate;
    }

    public Map<String, Boolean> getClicked(){
        return clicked;
    }

    public void setClicked(Map<String, Boolean> clicked){
        this.clicked = clicked;
    }

    public Map<String, Boolean> getAgree() {
        return agree;
    }

    public void setAgree(Map<String, Boolean> agree) {
        this.agree = agree;
    }

    public Map<String, Boolean> getBookmark() {
        return bookmark;
    }

    public void setBookmark(Map<String, Boolean> bookmark) {
        this.bookmark = bookmark;
    }

    public Map<String, Boolean> getPushalarm() {
        return pushalarm;
    }

    public void setPushalarm(Map<String, Boolean> pushalarm) {
        this.pushalarm = pushalarm;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getRecommend() {
        return recommend;
    }

    public void setRecommend(long recommend) {
        this.recommend = recommend;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
