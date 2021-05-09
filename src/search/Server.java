package search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread{
    private BlockingQueue<Search> queue = new LinkedBlockingQueue<>();
    private ResultListener listener;

    public Server(ResultListener listener) {
        this.listener = listener;
        start();
    }

    public void run() {
        while (true) {
            try {
                execute(queue.take());
            }catch (InterruptedException e) {}
        }
    }

    public void add(Search search) throws Exception {
        queue.put(search);
    }

    private void execute(Search search){
        listener.executed(search);
    }
}
