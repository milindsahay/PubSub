package models;

import models.subscriber.ISubscriber;
import models.subscriber.SubsriberWorker;

import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String id;
    private final List<Message> messages;
    private final List<SubsriberWorker> subscribers;

    public String getId() {
        return id;
    }

    public Topic(String id) {
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<SubsriberWorker> getSubscribers() {
        return subscribers;
    }

    public void addMessages(Message message) {
        messages.add(message);
    }

    public void addSubscribers(SubsriberWorker subscriber) {
         subscribers.add(subscriber);
    }
}
