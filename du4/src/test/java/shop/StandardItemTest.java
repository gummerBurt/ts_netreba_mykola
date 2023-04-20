package shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class StandardItemTest {
    private int id;
    private String name;
    private int price;
    private String category;
    private int loyaltyPoints;
    private StandardItem expectedItem;

    private StandardItem item1;

    public StandardItemTest(int id, String name, int price, String category, int loyaltyPoints, StandardItem expectedItem){
        super();
        this.id = id;
        this.price = price;
        this.name = name;
        this.category = category;
        this.loyaltyPoints = loyaltyPoints;
        this.expectedItem = expectedItem;
    }

    @Parameterized.Parameters
    public static Collection input(){
        return Arrays.asList(new Object[][] { {1, "test1", 50, "catTest1", 5, new StandardItem(1, "test1", 50, "catTest1", 5)}, {2, "test2", 52, "catTest2", 7, new StandardItem(2, "test2", 52, "catTest2", 7)}});
    }

    @Test
    public void StandardItemTestConstructor(){
        StandardItem item = new StandardItem(1, "test", 50, "catTest", 5);
        assertEquals(1, item.getID());
        assertEquals("test", item.getName());
        assertEquals(50, item.getPrice(), 0);
        assertEquals("catTest", item.getCategory());
        assertEquals(5, item.getLoyaltyPoints());
    }

    @Test
    public void StandardItemTestConstructorNullValues(){
        StandardItem item = new StandardItem(0, "test", 0, "catTest", 0);
        assertEquals(0, item.getID());
        assertEquals("test", item.getName());
        assertEquals(0, item.getPrice(), 0);
        assertEquals("catTest", item.getCategory());
        assertEquals(0, item.getLoyaltyPoints());
    }

    @Test
    public void copyTest(){
        StandardItem item = new StandardItem(1, "test", 50, "catTest", 5);

        StandardItem item2 = item.copy();

        assertEquals(item, item2);
    }

    @Test
    public void equalsTest(){
        item1 = expectedItem.copy();
        assertTrue(item1.equals(expectedItem));
    }

    @Test
    public void equalsTestFails(){
        item1 = new StandardItem(1, "test", 50, "catTest", 5);
        assertFalse(item1.equals(expectedItem));
    }
}
