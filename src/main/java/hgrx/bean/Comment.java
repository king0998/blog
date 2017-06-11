package hgrx.bean;

/**
 * Created by HGRX on 2017/6/12
 */
public class Comment {

    private Long id;
    private int entityType;
    private Long entityId;
    private String nickname;
    private String email;
    private String content;
    private Long createdDate;

    public Comment() {

    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}


