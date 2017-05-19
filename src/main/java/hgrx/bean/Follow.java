package hgrx.bean;

/**
 * Created by HGRX on 2017/5/15
 */
public class Follow {
    private Long id;
    private Long mainUserId;
    private Long followerId;

    public Follow() {
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", mainUserId=" + mainUserId +
                ", followerId=" + followerId +
                '}';
    }

    /**
     * @param mainUserId 被关注人
     * @param followerId 关注人
     */
    public Follow(Long mainUserId, Long followerId) {
        this.mainUserId = mainUserId;
        this.followerId = followerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainUserId() {
        return mainUserId;
    }

    public void setMainUserId(Long mainUserId) {
        this.mainUserId = mainUserId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long followerId) {
        this.followerId = followerId;
    }
}
