package dev.koh.practice.designPatterns.creational.builder;

import dev.koh.libs.utils.MyTimer;

import java.util.List;

class FoodOrder {

    private int totalItems;
    private List<Item> itemList;
    private String promoCode;

    FoodOrder(OrderBuilder orderBuilder) {
        this.totalItems = orderBuilder.getTotalItems();
        this.itemList = orderBuilder.getItemList();
        this.promoCode = orderBuilder.getPromoCode();
    }

    void printTimeTaken() {
        MyTimer myTimer = new MyTimer();
        myTimer.startTimer();
        myTimer.stopTimer(true);
    }

    void printOrderDetails() {

        System.out.println("Total Items: " + this.totalItems);
        this.itemList.forEach(Item::displayItemDetails);
        System.out.println("Promo Code : " + this.promoCode);

    }

    int getTotalItems() {
        return totalItems;
    }

    List<Item> getItemList() {
        return itemList;
    }

    String getPromoCode() {
        return promoCode;
    }
}

class OrderBuilder {

    private int totalItems;
    private List<Item> itemList;
    private String promoCode;

    FoodOrder build() {
        System.out.println("Instantiating FoodOrder");
        return new FoodOrder(this);
    }

    OrderBuilder configTotalItems(int totalItems) {
        this.totalItems = totalItems;
        return this;
    }

    OrderBuilder configItemList(List<Item> mainCourseList) {
        this.itemList = mainCourseList;
        return this;
    }

    OrderBuilder configPromoCode(String promoCode) {
        this.promoCode = promoCode;
        return this;
    }

    int getTotalItems() {
        return totalItems;
    }

    List<Item> getItemList() {
        return itemList;
    }

    String getPromoCode() {
        return promoCode;
    }
}
