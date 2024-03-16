import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer's shopping cart.
 */
class ShoppingCartOBJ13 {
    private List<CartItem> items; // Item class not visible in this screenshot
    public ShoppingCartOBJ13() {
        this.items = new ArrayList<>(); 
    }

    /**
     * Adds an item to the shopping cart.
     * @param item The item to add.
     */
    public void addItem(CartItem item) {
        items.add(item);
    }
    // ... Additional methods like, removeItem, calculateTotal, etc.
    
    /**
     * Returns a copy of this cart's items.
     * Modifications to the returned list will not affect the cart.
     * 
     * @return A copy of this cart's items.
     */
    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Return a copy
    }
}

public class OBJ13 {
    public static void main(String[] args) {
        ShoppingCartOBJ13 cart = new ShoppingCartOBJ13();
        cart.addItem(new CartItem("Coffee", 3.99, 2));
        cart.addItem(new CartItem("Tea", 2.50, 1));

        List<CartItem> cartContents = cart.getItems();

        System.out.println("Cart Contents:");
        for (CartItem item : cartContents) {
            System.out.println(item.getName() + " - $" + item.getPrice() + " - Quanity: " + item.getQuantity());
        }
    }
}

class CartItem {
    private String name;
    private double price;
    private int quantity;

    public CartItem(String name, double price, int quantity) {
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
    public int getQuantity() {
        return quantity;
    }
    // Additional getters and Setters here ...
}
