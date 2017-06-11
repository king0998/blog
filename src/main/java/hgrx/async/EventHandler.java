package hgrx.async;

import java.util.List;

/**
 * Created by HGRX on 2017/6/10
 */
public interface EventHandler {
    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}

