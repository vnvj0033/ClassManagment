package search;

import java.util.Timer;
import java.util.TimerTask;

public class SearchScheduler {
    private ResultsListener listener;
    private Timer timer;

    public SearchScheduler (ResultsListener listener){
        this.listener = listener;
    }

    public void repeat(final Search search, long interval) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                search.execute();
                listener.executed(search);
            }
        };
        timer.scheduleAtFixedRate(task, 0, interval);
    }

    public void stop(){
        timer.cancel();
    }
}