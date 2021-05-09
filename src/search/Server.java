package search;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Server extends Thread{
    private List<Search> queue = Collections.synchronizedList(new LinkedList<>());
    private ResultListener listener;

    public Server(ResultListener listener) {
        this.listener = listener;
        start();
    }

    public void run() {
        while (true) {
            if (!queue.isEmpty())
                execute(queue.remove(0));
            Thread.yield();
        }
    }

    public void add(Search search) {
        queue.add(search);
    }

    private void execute(Search search){
        listener.executed(search);
    }
}
