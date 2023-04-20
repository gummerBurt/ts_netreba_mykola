package archive;

import org.junit.Test;
import shop.Item;
import shop.Order;
import shop.ShoppingCart;
import shop.StandardItem;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PurchasesArchiveTest {

    private  final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void printItemPurchaseStatisticsTestEmpty(){
        PurchasesArchive archive = new PurchasesArchive();
        archive.printItemPurchaseStatistics();
        assertEquals("", new ByteArrayOutputStream().toString());
    }

    @Test
    public void printItemPurchaseStatisticsTest(){
        StandardItem item = new StandardItem(1, "test", 50, "catTest", 5);
        PurchasesArchive archive = prepareArchive(item);
        System.setOut(new PrintStream(outContent));
        archive.printItemPurchaseStatistics();

        assertEquals("ITEM PURCHASE STATISTICS:\n" +
                "ITEM  Item   ID 1   NAME test   CATEGORY catTest   PRICE 50.0   LOYALTY POINTS 5   HAS BEEN SOLD 1 TIMES", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void getHowManyTimesHasBeenItemSoldTest(){
        StandardItem item = new StandardItem(1, "test", 50, "catTest", 5);
        PurchasesArchive archive = prepareArchive(item);
        assertEquals(1, archive.getHowManyTimesHasBeenItemSold(item));
    }

    @Test
    public void putOrderToPurchasesArchiveTest(){
        PurchasesArchive archive = new PurchasesArchive();
        StandardItem item = new StandardItem(1, "test", 50, "catTest", 5);
        Order order = new Order(new ShoppingCart(), "", "");
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        order.setItems(items);

        assertEquals(0, archive.getHowManyTimesHasBeenItemSold(item));
        archive.putOrderToPurchasesArchive(order);
        assertEquals(1, archive.getHowManyTimesHasBeenItemSold(item));
    }

    public PurchasesArchive prepareArchive(StandardItem item){
        HashMap<Integer, ItemPurchaseArchiveEntry> itemPurchaseArchive = new HashMap<>();
        ItemPurchaseArchiveEntry itemPurchaseArchiveEntry = new ItemPurchaseArchiveEntry(item);
        itemPurchaseArchive.put(1, itemPurchaseArchiveEntry);

        Order order = new Order(new ShoppingCart(), "", "");
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        order.setItems(items);
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);

        PurchasesArchive archive = new PurchasesArchive(itemPurchaseArchive, orders);
        return archive;
    }
}
