package hgrx.bean;


import hgrx.entity.ArticleDetailVO;

/**
 * Created by HGRX on 2017/5/11
 */
public class Article {
    private Long id;
    private Long userId;
    private String title;
    private Long timestamp;
    private String content;
    private Boolean draft;

    public Article() {
    }

    public Article(Long userId, String title, Long timestamp, String content) {
        this.userId = userId;
        this.title = title;
        this.timestamp = timestamp;
        this.content = content;
    }

    public Article(ArticleDetailVO advo) {
        id = advo.getId();
        userId = advo.getUserId();
        title = advo.getTitle();
        timestamp = advo.getTimestamp();
        content = advo.getContent();
        draft = advo.getDraft();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDraft() {
        return draft;
    }

    public void setDraft(Boolean draft) {
        this.draft = draft;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
