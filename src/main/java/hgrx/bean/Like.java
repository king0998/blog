package hgrx.bean;

/**
 * Created by HGRX on 2017/5/19
 */
public class Like {
    private Long id;
    private Long articleId;
    private Long userId;


    public Like() {
    }

    public Like(Long articleId, Long userId) {
        this.articleId = articleId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", userId=" + userId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
