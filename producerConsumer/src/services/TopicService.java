package services;

import models.Message;
import models.Topic;
import workerHandler.TopicSubscriberWorkerHandler;

import java.util.HashMap;

public class TopicService {
    private static TopicService topicService;

    private static Object lock = new Object();

    private HashMap<String, TopicSubscriberWorkerHandler> subscriberToHandlerMap = new HashMap<>();

    private TopicService(){};

    //singleton class
    public static TopicService getInstance(){
        if (topicService == null){
            synchronized (lock){
                if (topicService == null){
                    topicService = new TopicService();
                }
            }
        }
        return topicService;
    }

    public void publish(Topic topic, Message message){
        topic.getMessages().add(message);
        System.out.println("Publishing the message to topic " + topic.getId() + " with message " + message.getMessage());
        for (var subscriber : topic.getSubscribers()){
            TopicSubscriberWorkerHandler subscriberHandler;
            if (!subscriberToHandlerMap.containsKey(subscriber.getSubscriber().getId())){
                subscriberHandler = new TopicSubscriberWorkerHandler(topic, subscriber);
                subscriberToHandlerMap.put(subscriber.getSubscriber().getId(), subscriberHandler);
                new Thread(() -> subscriberHandler.consume()).start();
            }
            else {
                subscriberHandler = subscriberToHandlerMap.get(subscriber.getSubscriber().getId());
                subscriberHandler.nudge();
            }
        }

    }
}
