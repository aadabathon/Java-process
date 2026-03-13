import java.util.ArrayList;

public class ShoppingCart {
    private String customerName;
    private String currentDate;
    private ArrayList<ItemToPurchase> cartItems = new ArrayList<>();

    public ShoppingCart() {
        this.customerName = "none";
        this.currentDate = "January 1, 2016";
        this.cartItems = new ArrayList<>();
    }

    public ShoppingCart(String customerName, String currentDate) {
        this.customerName = customerName;
        this.currentDate = currentDate;
        this.cartItems = new ArrayList<>();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return currentDate;
    }

    public void addItem(ItemToPurchase item) {
        cartItems.add(item);
    }

    public void removeItem(String itemName) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getName().equals(itemName)) {
                cartItems.remove(i);
                return;
            }
        }
        System.out.println("Item not found in cart. Nothing removed.");
    }

    public void modifyItem(ItemToPurchase item) {
        if (item == null) return;
        for (ItemToPurchase cartItem : cartItems) {
            if (cartItem.getName().equals(item.getName())) {
                if (!item.getDescription().equals("none")) {
                    cartItem.setDescription(item.getDescription());
                }
                if (item.getPrice() != 0) {
                    cartItem.setPrice(item.getPrice());
                }
                if (item.getQuantity() != 0) {
                    cartItem.setQuantity(item.getQuantity());
                }
                return;
            }
        }
        System.out.println("Item not found in cart. Nothing modified.");
    }

    public int getNumItemsInCart() {
        int totalQuantity = 0;
        for (ItemToPurchase item : cartItems) {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public int getCostOfCart() {
        int totalCost = 0;
        for (ItemToPurchase item : cartItems) {
            totalCost += item.getPrice() * item.getQuantity();
        }
        return totalCost;
    }

    public void printTotal() {
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println("Number of Items: " + getNumItemsInCart());
        if (cartItems.isEmpty()) {
            System.out.println("SHOPPING CART IS EMPTY");
            System.out.println();
        } else {
            for (ItemToPurchase item : cartItems) {
                item.printItemCost();
            }
        }
        System.out.println("Total: $" + getCostOfCart());
    }

    public void printDescriptions() {
        System.out.println(customerName + "'s Shopping Cart - " + currentDate);
        System.out.println("Item Descriptions");
        for (ItemToPurchase item : cartItems) {
            item.printItemDescription();
        }
    }
}
