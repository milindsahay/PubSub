import models.Message;
import models.Topic;
import models.subscriber.SleepingSubscriber;
import models.subscriber.SubsriberWorker;
import services.TopicService;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        TopicService topicService = TopicService.getInstance();
        SubsriberWorker subsriberWorker1 = new SubsriberWorker(new SleepingSubscriber("1", 3000));
        SubsriberWorker subsriberWorker2 = new SubsriberWorker(new SleepingSubscriber("2", 6000));

        Topic topic1 = new Topic("1");



        topic1.addSubscribers(subsriberWorker1);
        topic1.addSubscribers(subsriberWorker2);
        ExecutorService executors = Executors.newFixedThreadPool(6);

        for(int i=0;i<6;i++){
            Message message = new Message("Hello World at " + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            executors.submit(() -> topicService.publish(topic1, message));
        }

    }
}