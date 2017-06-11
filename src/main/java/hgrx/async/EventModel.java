package hgrx.async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HGRX on 2017/6/10
 */

public class EventModel {
    private EventType type;
    private Long actorId;
    private int entityType;
    private Long entityId;
    private Long entityOwnerId;

    private Map<String, String> exts = new HashMap<>();

    @Override
    public String toString() {
        return "EventModel{" +
                "type=" + type +
                ", actorId=" + actorId +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", entityOwnerId=" + entityOwnerId +
                ", exts=" + exts +
                '}';
    }

    public EventModel() {

    }

    public EventModel setExt(String key, String value) {
        exts.put(key, value);
        return this;
    }

    public EventModel(EventType type) {
        this.type = type;
    }

    public String getExt(String key) {
        return exts.get(key);
    }


    public EventType getType() {
        return type;
    }

    public EventModel setType(EventType type) {
        this.type = type;
        return this;
    }

    public Long getActorId() {
        return actorId;
    }

    public EventModel setActorId(Long actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public Long getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public Long getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(Long entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public EventModel setExts(Map<String, String> exts) {
        this.exts = exts;
        return this;
    }
}
