package db;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.RandomAccessFile;

public class DataFile {
    public static final String DATA_EXT = ".db";
    public static final String KEY_EXT = ".idx";

    private String dataFilename;
    private String keyFilename;

    private RandomAccessFile db;
    private KeyFile keys;


    public static DataFile create(String filebase) throws IOException {
        return new DataFile(filebase, true);
    }

    public static DataFile open(String filebase) throws IOException {
        return new DataFile(filebase, false);
    }

    private DataFile(String failbase, boolean deleteFiles) {
        dataFilename = failbase + DATA_EXT;
        keyFilename = failbase + KEY_EXT;

        if (deleteFiles)
            deleteFiles();
        openFiles();
    }

    public void add(String key, Object object) throws IOException {
        long position = db.length();

        byte[] bytes = getBytes(object);
        db.seek(position);
        db.write(bytes, 0, bytes.length);

        keys.add(key, position, bytes.length);
    }

    public Object findBy(String id) throws IOException {
        if (!keys.constainsKey(id))
            return null;

        long position = keys.getPosition(id);
        db.seek(position);

        int length = keys.getLength(id);
        return read(length);
    }

    public int size(){
        return keys.size();
    }

    public void close()throws IOException {
        keys.close();
        db.close();
    }

    public void deleteFiles() {
        IOUtill.delete(dataFilename, keyFilename);
    }

    private Object read(int length) throws IOException {
        byte[] bytes = new byte[length];
    }
}
