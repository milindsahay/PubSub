package models.subscriber;

import models.Message;

public interface ISubscriber {
    String getId();

    void consume(Message message);
}
