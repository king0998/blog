package hgrx.async;

/**
 * Created by HGRX on 2017/6/10
 */

public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3),
    FOLLOW(4),
    UNFOLLOW(5),
    ADD_QUESTION(6);

    private int value;

    EventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
