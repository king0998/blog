package hgrx.dto;

import hgrx.bean.Article;
import hgrx.bean.Star;
import hgrx.util.MyUtils;

import java.util.List;

/**
 * Created by HGRX on 2017/5/14
 */
public class ArticleDetailVO implements Comparable<ArticleDetailVO> {
    private long id;
    private long userId;
    private String nickname;
    private String title;
    private List<String> tags;
    private long timestamp;
    private String content;
    private Boolean draft;

    private int likeNum;    //点赞
    private int starNum;    //收藏

    public ArticleDetailVO() {

    }

    public ArticleDetailVO(Long id) {
        this.id = id;
    }

    public ArticleDetailVO(Article article) {
        id = article.getId();
        userId = article.getUserId();
        title = article.getTitle();
        timestamp = article.getTimestamp();
        content = article.getContent();
        draft = article.getDraft();
    }

    public ArticleDetailVO(Star star) {
        this.id = star.getArticleId();
        this.userId = star.getUserId();
    }


    //tags : java,linux,spring
    private void setTags(String tagsStr) {
        tags = MyUtils.transformTagsToList(tagsStr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleDetailVO that = (ArticleDetailVO) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public int compareTo(ArticleDetailVO o) {
        if (o == null) {
            return -1;
        }
        return (int) (o.getTimestamp() - this.getTimestamp());
    }

    @Override
    public String toString() {
        return "ArticleDetailVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                ", likeNum=" + likeNum +
                ", starNum=" + starNum +
                '}';
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
