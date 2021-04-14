package testing;

import java.util.ArrayList;
import java.util.List;

public class SuiteBuilder {
    public List<String> getherTestClassNames() {
        Class c = getClass();
        List<String> list = new ArrayList<>();
        list.add(c.getName());
        return list;
    }
}
