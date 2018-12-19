package pizzabot;

import java.util.*;

public class Order {

    private final static Comparator<Item> itemComparator = Comparator.comparing(Item::getDiscount)
            .thenComparing(Item::getPrice)
            .reversed();

    private float price = 0;
    private List<Item> itemsList = new ArrayList<>();

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Map<Item, Integer> getItemsQuantityMap() {
        Map<Item, Integer> itemsQuantityMap = new TreeMap<>(itemComparator);
        for (Item item:itemsList) {
            itemsQuantityMap.merge(item, 1, (a, b) -> a + b);
        }
        return itemsQuantityMap;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }

    public void add(Item item) {
        itemsList.add(item);
        price = price + item.getPrice();
    }

    public void sort() {
        itemsList.sort(itemComparator);
    }

}
