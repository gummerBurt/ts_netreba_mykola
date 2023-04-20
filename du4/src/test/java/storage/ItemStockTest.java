package storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import shop.StandardItem;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ItemStockTest {
    private int x;
    private int expectedCount;
    private boolean isPlus;
    private static final ItemStock stock = new ItemStock(new StandardItem(5, "name", 50.9F, "cat", 7));

    public ItemStockTest(int x, int expectedCount, boolean isPlus){
        super();
        this.x = x;
        this.expectedCount = expectedCount;
        this.isPlus = isPlus;
    }


    @Test
    public void ItemStockConstructorTest(){
        StandardItem item = new StandardItem(5, "name", 50.9F, "cat", 7);
        ItemStock stock = new ItemStock(item);
        assertEquals(item, stock.getItem());
        assertEquals(0, stock.getCount());
    }

    @Parameterized.Parameters
    public static Collection inputIncreaseItemCount(){
        return Arrays.asList(new Object[][]{{1, 1, true}, {1, 0, false}, {5, 5, true}, {3, 2, false}, {10, 12, true}, {7, 5, false}});
    }

    @Test
    public void itemCountTest(){
        if(isPlus) {
            stock.IncreaseItemCount(x);
            assertEquals(expectedCount, stock.getCount());
        }else {
            stock.decreaseItemCount(x);
            assertEquals(expectedCount, stock.getCount());
        }
    }

}
