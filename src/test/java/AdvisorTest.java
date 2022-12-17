import com.beust.ah.A;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class AdvisorTest {
    AdvisorTest() throws IOException {
    }

    @Test
    void testfName() {
        Advisor advisorTest = new Advisor();
        advisorTest.setfName("Müjdat");
        assertEquals("Müjdat",advisorTest.getfName());
    }

    @Test
    void testlName() {
        Advisor advisorTest = new Advisor();
        advisorTest.setlName("SOYTÜRK");
        assertEquals("SOYTÜRK",advisorTest.getlName());
    }

    @Test
    void testAdvisorId() {
        Advisor advisorTest = new Advisor();
        advisorTest.setAdvisorId(4);
        assertEquals(4,advisorTest.getAdvisorId());
    }


}