package hgrx.bean;


/**
 * 向article_tags_link表插入时用到的QUERY对象，不然每次都构造一个HashMap感觉有点傻
 */
public class ArticleTagsLink {

    private Long id;
    private Long articleId;
    private Long tagId;

    public ArticleTagsLink(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleTagsLink{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", tagId=" + tagId +
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

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }
}
