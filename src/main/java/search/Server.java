package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server extends Thread {
    private BlockingQueue<Search> queue = new LinkedBlockingQueue<>();
    private ResultsListener listener;
    static final String START_MSG = "started";
    static final String END_MSG = "finished";

    private static ThreadLocal<List<String>> threadLog = new ThreadLocal<>(){
        protected List<String> initialValue(){
            return new ArrayList<String>();
        }
    };

    private List<String> completeLog = Collections.synchronizedList(new ArrayList<String>());

    public Server(ResultsListener listener) {
        this.listener = listener;
        start();
    }

    public void run() {
        while (true) {
            try {
                execute(queue.take());
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void add(Search search) throws Exception {
        queue.put(search);
    }

    public List<String> getLog(){
        return completeLog;
    }

    public void execute(final Search search){
        new Thread(new Runnable() {
            public void run() {
                log(START_MSG, search);
                search.execute();
                log(END_MSG, search);
                listener.executed(search);
                completeLog.addAll(threadLog.get());
            }
        }).start();
    }

    private void log(String message, Search search){
        threadLog.get().add(search + " " + message + " at" + new Date());
    }

    public void shutDown() throws Exception {
        this.interrupt();
    }
}
