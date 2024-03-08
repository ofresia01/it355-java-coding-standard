import java.util.ArrayList;
import java.util.List;
/**
 * Represents a customer's shopping cart.
 */
class ShoppingCart {
    private List<Item> items; // Item class not visible in this screenshot
    public ShoppingCart() {
        this.items = new ArrayList<>(); 
    }

    /**
     * Adds an item to the shopping cart.
     * @param item The item to add.
     */
    public void addItem(Item item) {
        items.add(item);
    }
    // ... Additional methods like, removeItem, calculateTotal, etc.
    
    /**
     * Returns a copy of this cart's items.
     * Modifications to the returned list will not affect the cart.
     * 
     * @return A copy of this cart's items.
     */
    public List<Item> getItems() {
        return new ArrayList<>(items); // Return a copy
    }
}

public class OBJ13 {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Coffee", 3.99, 2));
        cart.addItem(new Item("Tea", 2.50, 1));

        List<Item> cartItems = cart.getItems();

        System.out.println("Cart Contents:");
        for (Item item : cartItems) {
            System.out.println(item.getName() + " - $" + item.getPrice());
        }
    }
}

class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Immutable, so we can just return the object.
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    // Additional getters and Setters here ...
}
