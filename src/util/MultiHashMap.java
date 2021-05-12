package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiHashMap<K, V> {
    private Map<K, List<V>> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    public void put(K key, V value) {
        List<V> values = map.get(key);
        if (values == null) {
            values = new ArrayList<>();
            map.put(key, values);
        }
        values.add(value);
    }

    public List<V> get(K key) {
        return map.get(key);
    }
}
