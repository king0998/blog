package hgrx.util;

/**
 * Created by HGRX on 2017/6/7
 */
public enum CacheIdentity {
    LIST_TAGS_WITH_USERID("listTags-"),
    LIST_ALL_TAGS("listAllTags"),
    USER_STAR_LIST_WITH_USERID("userStarList-");


    String msg;

    CacheIdentity(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
