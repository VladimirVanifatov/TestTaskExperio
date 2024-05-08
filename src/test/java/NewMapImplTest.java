import org.example.NewMap;
import org.example.NewMapImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class NewMapImplTest {
    private static final NewMap<Integer, String> testMap = new NewMapImpl<>();


    @BeforeAll
    public static void prepareTestData() {
        testMap.put(1, "First value");
        testMap.put(2, "Second value");
        testMap.put(3, "Third value");
        testMap.put(4, "Fourth value");
    }

    @Test
    public void ifPresent_WithExistingKey() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream oldStream = System.out;
        System.setOut(new PrintStream(output));
        testMap.ifPresent(1, System.out::print);
        Assertions.assertEquals("First value", output.toString());
        System.setOut(oldStream);
    }

    @Test
    public void ifPresent_WithNonExistingKey() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream oldStream = System.out;
        System.setOut(new PrintStream(output));
        testMap.ifPresent(25, System.out::print);
        Assertions.assertEquals("", output.toString());
        System.setOut(oldStream);
    }

    @Test
    public void orElse_WithExistingKey() {
        String value = testMap.orElse(2, "Other value");
        Assertions.assertEquals("Second value", value);
    }

    @Test
    public void orElse_WithNonExistingKey() {
        String value = testMap.orElse(25, "Other value");
        Assertions.assertEquals("Other value", value);
    }

    @Test
    public void orElseThrow_WithExistingKey() {
        String value = testMap.orElseThrow(3, NoSuchElementException::new);
        Assertions.assertEquals("Third value", value);
    }


    @Test
    public void orElseThrow_WithNonExistingKey() {
        Assertions.assertThrows(NoSuchElementException.class, () -> testMap.orElseThrow(20, NoSuchElementException::new));
    }

}
