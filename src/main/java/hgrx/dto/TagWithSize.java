package hgrx.dto;

import hgrx.bean.Tag;

/**
 * 显示在侧边栏,并带有当前标签下有多少篇文章
 * 每次都去遍历有点傻,考虑使用缓存存下来
 */
public class TagWithSize {
    private Long id;
    private String name;
    private String intro;
    private Long userId;

    private int size;

    public TagWithSize() {

    }

    public TagWithSize(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.userId = tag.getUserId();
        this.intro = tag.getIntro();
    }

    public TagWithSize(Tag tag, int size) {
        this.id = tag.getId();
        this.name = tag.getName();
        this.userId = tag.getUserId();
        this.intro = tag.getIntro();
        this.size = size;
    }

    @Override
    public String toString() {
        return "TagWithSize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", userId='" + userId + '\'' +
                ", size=" + size +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
