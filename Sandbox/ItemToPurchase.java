public class ItemToPurchase {

    private String itemName;
    private int itemPrice;
    private int itemQuantity;
    private String itemDescription;


public ItemToPurchase(String name, String description, int price, int quantity) {
        this.itemName = name;
        this.itemDescription = description;
        this.itemPrice = price;
        this.itemQuantity = quantity;
}

public String getDescription(){
    return itemDescription;
}
public void setDescription(String Description){
    this.itemDescription = Description;
}
public void printItemCost(){
    System.out.println(itemName +  " " + itemQuantity + " @ $" + itemPrice + " = $" + (itemPrice * itemQuantity));
}
public void printItemDescription(){
    System.out.println(itemName + ": " + itemDescription);
}

public ItemToPurchase() {
        this.itemName = "none";
        this.itemPrice = 0;
        this.itemQuantity = 0;
    }
public void setName(String itemName) {
        this.itemName = itemName;
    }
public void setPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
public void setQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
public String getName() {
        return itemName;
    }

    public int getPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return itemQuantity;
    }
}