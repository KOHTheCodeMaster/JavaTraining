package dev.koh.practice.designPatterns.creational.builder;

import java.util.Arrays;
import java.util.List;

public class BuilderDemo {

    public static void main(String[] args) {

        //  Time Stamp : 15th June 2K19, 01:00 PM..!!

        System.out.println("Begin.");

        //  Instantiate ItemBuilder using Singleton Pattern
        ItemBuilder itemBuilder = ItemBuilder.getInstance();

        //  Instantiate Item using Builder Pattern
        itemBuilder.configItemName("Crispy Chicken Meal").configVariant("Medium").configQty(1).configPrice(130);
        Item item1 = itemBuilder.build();

        //  Re-use same ItemBuilder instance by resetting it
        itemBuilder.resetConfiguration();
        itemBuilder.configItemName("Chicken Whopper").configQty(1).configPrice(200);
        Item item2 = itemBuilder.build();

        //  Instantiate FoodOrder using Builder Pattern
        List<Item> itemList = Arrays.asList(item1, item2);
        OrderBuilder orderBuilder = new OrderBuilder();
        orderBuilder.configTotalItems(itemList.size()).configItemList(itemList).configPromoCode("EDTMW");

        FoodOrder obj = orderBuilder.build();
        obj.printTimeTaken();
        obj.printOrderDetails();

        System.out.println("\nEnd.");

    }

}

class ItemBuilder {

    private static ItemBuilder instance;
    private String itemName;
    private String variant;
    private int qty;
    private double price;

    private ItemBuilder() {

    }

    //  <| ================================ Singleton Pattern for Item Builder ================================ |>

    static ItemBuilder getInstance() {
        if (instance == null) synchronized (ItemBuilder.class) {
            if (instance == null) instance = new ItemBuilder();
        }
        return instance;
    }

    Item build() {
        return new Item(this);
    }

    //  <| ================================ Item Configuration ================================ |>

    ItemBuilder configItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    ItemBuilder configVariant(String variant) {
        this.variant = variant;
        return this;
    }

    ItemBuilder configQty(int qty) {
        this.qty = qty;
        return this;
    }

    ItemBuilder configPrice(double price) {
        this.price = price;
        return this;
    }

    void resetConfiguration() {
        this.variant = null;
        this.itemName = null;
        this.qty = 0;
        this.price = 0.0;
    }

    //  <| ================================ Getters & Setters ================================ |>

    String getItemName() {
        return itemName;
    }

    String getVariant() {
        return variant;
    }

    int getQty() {
        return qty;
    }

    double getPrice() {
        return price;
    }
}

class Item {

    private String itemName;
    private String variant;
    private int qty;
    private double price;

    Item(ItemBuilder itemBuilder) {
        this.itemName = itemBuilder.getItemName();
        this.variant = itemBuilder.getVariant();
        this.qty = itemBuilder.getQty();
        this.price = itemBuilder.getPrice();
    }

    void displayItemDetails() {

        System.out.println("[Item Details] :=> \n");
        System.out.println("Name : " + this.itemName);
        System.out.println("Variant : " + this.variant);
        System.out.println("Qty.: " + this.qty);
        System.out.println("Price: " + this.price);
        System.out.println();

    }

    //  <| ================================ Getters & Setters ================================ |>

    String getItemName() {
        return itemName;
    }

    String getVariant() {
        return variant;
    }

    int getQty() {
        return qty;
    }

    double getPrice() {
        return price;
    }
}

/*
 *  Time Stamp : 15th June 2K19, 01:25 PM..!!
 *
 *  Builder Pattern..!!
 *  For simply instantiating objects with different properties
 *  One could use Telescopic Constructors & provide exposed getters & setters for flexibility
 *  But this leads to creating an instance which is Mutable due to its exposed setters!
 *
 *  Hence, to solve this problem, Builder Pattern comes to rescue
 *  It allows to instantiate the object as per desired configuration (properties) & keeping it Immutable!
 *
 *  <| ================================================================ |>
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */















