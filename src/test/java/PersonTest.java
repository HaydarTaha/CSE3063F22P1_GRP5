import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PersonTest {

    private Person personUnderTest;

    @BeforeEach
    void setUp() {
        personUnderTest = new Person() {
        };
    }

    @Test
    void testGetfName() {
        assertNull(personUnderTest.getfName());
    }

    @Test
    void testGetlName() {
        assertNull(personUnderTest.getlName());
    }
}
