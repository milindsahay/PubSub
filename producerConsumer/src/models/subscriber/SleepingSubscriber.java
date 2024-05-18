package models.subscriber;

import models.Message;

public class SleepingSubscriber implements ISubscriber{
    private String id;
    private int sleepingTime;

    public SleepingSubscriber(String id, int sleepingTime){
        this.id = id;
        this.sleepingTime = sleepingTime;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void consume(Message message) {
        try {
            Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Consuming the message for subscriber " + id + " with message " + message.getMessage());
    }
}
