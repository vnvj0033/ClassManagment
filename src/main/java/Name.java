import java.util.ArrayList;
import java.util.List;

public class Name {
    private String fullName;
    private String firstName;
    private String lastName;

    public Name(String fullName) {
        this.fullName = fullName;
        List<String> name = split(fullName);
        if (name.size() != 2) return;
        firstName = name.get(0);
        lastName = name.get(1);
    }

    private List<String> split(String fullName) {
        List<String> results = new ArrayList<>();
        for (String name : fullName.split("\\s+")) results.add(name);
        return results;
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
