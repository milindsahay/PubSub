package models.subscriber;

import java.util.concurrent.atomic.AtomicInteger;

//composition
public class SubsriberWorker {
    private AtomicInteger offset;

    private ISubscriber subscriber;

    public AtomicInteger getOffset() {
        return offset;
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public SubsriberWorker(ISubscriber subscriber){
        this.subscriber = subscriber;
        offset = new AtomicInteger(0);
    }
}
