import cz.cvut.fel.ts1.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactTest {
    @Test
    public void factorialTest() {
        Assertions.assertEquals(2, Main.factorialRecursive(2));
    }
}