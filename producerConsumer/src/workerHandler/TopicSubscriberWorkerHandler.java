package workerHandler;

import models.Topic;
import models.subscriber.SubsriberWorker;

public class TopicSubscriberWorkerHandler {
    private Topic topic;
    private SubsriberWorker subsriberWorker;

    public TopicSubscriberWorkerHandler(Topic topic, SubsriberWorker subsriberWorker) {
        this.topic = topic;
        this.subsriberWorker = subsriberWorker;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setSubsriberWorker(SubsriberWorker subsriberWorker) {
        this.subsriberWorker = subsriberWorker;
    }

    public Topic getTopic() {
        return topic;
    }

    public SubsriberWorker getSubsriberWorker() {
        return subsriberWorker;
    }

    public void consume(){
        while (true){
            synchronized (subsriberWorker){
                while (topic.getMessages().size() <= subsriberWorker.getOffset().get()){
                    try {
                        subsriberWorker.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                var offset = subsriberWorker.getOffset().getAndIncrement();
                subsriberWorker.getSubscriber().consume(topic.getMessages().get(offset));
                subsriberWorker.notifyAll();
            }
        }
    }

    public void nudge(){
        synchronized (subsriberWorker){
            subsriberWorker.notifyAll();
        }
    }
}
