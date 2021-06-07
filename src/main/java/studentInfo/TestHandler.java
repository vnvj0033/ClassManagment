package studentInfo;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class TestHandler extends Handler {
    private LogRecord record;

    public void flush() {}
    public void close() throws SecurityException { }
    public void publish(LogRecord record) {
        this.record = record;
    }

    String getMessage(){
        return record.getMessage();
    }
}
