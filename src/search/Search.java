package search;

import util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Search {
    private URL url;
    private String searhString;
    private int matches = 0;
    private Exception exception = null;

    public Search(String urlString, String searchString) throws IOException {
        this.url = new URL(urlString);
        this.searhString = searchString;
    }

    public String getText() {
        return searhString;
    }

    public String getUrl() {
        return url.toString();
    }

    public int matches() {
        return matches;
    }

    public boolean errored() {
        return exception != null;
    }

    public Exception getError() {
        return exception;
    }

    public void execute() {
        try {
            searhUrl();
        }catch (IOException e){
            e.printStackTrace();
            exception = e;
        }
    }

    private void searhUrl() throws IOException{
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null){
                matches += StringUtil.occurrences(line, searhString);
            }
        }finally {
            if (reader != null)
                reader.close();
        }
    }
}
