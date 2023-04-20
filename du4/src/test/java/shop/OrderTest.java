package shop;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class OrderTest {
    @Test
    public void OrderSecondConstructorTestFail() {
        assertThrows(NullPointerException.class, ()->new Order(null, "", ""));
    }

    @Test
    public void OrderSecondConstructorTest() {
        Order order = new Order(prepareCart(), "", "");
        assertEquals(0, order.getState());
    }

    @Test
    public void OrderFirstConstructorTestEmpty() {
        Order order = new Order(new ShoppingCart(), "", "", 2);
        assertEquals(0, order.getItems().size());
    }

    @Test
    public void OrderFirstConstructorTest() {
        ShoppingCart cart = prepareCart();
        Order order = new Order(cart, "", "", 2);
        assertEquals(cart.getCartItems(), order.getItems());
    }

    public ShoppingCart prepareCart(){
        ArrayList<Item> items = new ArrayList<Item>();
        StandardItem item = new StandardItem(99, "phone", 555.99F, "technika", 10);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(item);
        return cart;
    }
}
