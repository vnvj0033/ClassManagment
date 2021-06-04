import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTest {

    @Test
    public void testExtraneousSpaces() {
        final String fullName = "Jeffrey \t\t \n \r \f  Hyman";
        Name name = createName(fullName);
        assertEquals("Jeffrey", name.getFirstName());
        assertEquals("Hyman", name.getLastName());
    }

    private Name createName(String fullName) {
        Name name = new Name(fullName);
        assertEquals(fullName, name.getFullName());
        return name;
    }


}