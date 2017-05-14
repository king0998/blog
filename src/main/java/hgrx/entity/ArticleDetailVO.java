package hgrx.entity;

import hgrx.bean.Article;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by HGRX on 2017/5/14
 */
public class ArticleDetailVO {
    private long id;
    private long userId;
    private String username;
    private String title;
    private List<String> tags;
    private long timestamp;
    private String content;

    private int likeNum;    //点赞
    private int starNum;    //收藏

    public ArticleDetailVO(Article article) {
        id = article.getId();
        userId = article.getUserId();
        title = article.getTitle();
        setTags(article.getTags());
        timestamp = article.getTimestamp();
        content = article.getContent();
    }


    //tags : java,linux,spring
    private void setTags(String tagsStr) {
        tags = new ArrayList<>(Arrays.asList(tagsStr.split(",")));
    }


    @Override
    public String toString() {
        return "ArticleDetailVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", starNum=" + starNum +
                '}';
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }
}
