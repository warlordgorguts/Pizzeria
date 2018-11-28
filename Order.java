package pizzabot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Order extends Composite {

    private float price = 0;
    private List<Item> itemsEligibleDiscount = new ArrayList<>();
    //private List<Item> itemsDiscount = new ArrayList<>();
    private HashMap<Item, Integer> itemsQuantity = new HashMap<>();
    private HashMap<Item, Integer> discountedItemsQuantity = new HashMap<>();

    private DiscountCalculator discountCalculator;
    private float priceChange = 0;

    public Order(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void add(Item item) {
        //if (!getList().contains(item)) {
        //getList().add(item);
        itemsQuantity.merge(item, 1, (a, b) -> a + b);
        //item.setQuantity(item.getQuantity() + 1);
        if (discountCalculator.checkDiscountForProduct(item.getDiscount())) {
            itemsEligibleDiscount.add(item);
        }
        setPrice(getPrice() + item.getPrice());
    }

    /*
    private void sortByPrice(List<Item> listToSort) {

        listToSort.sort(Comparator.comparing(Item::getDiscount).thenComparing(Item::getPrice).reversed());
    }
    */

    private List<Item> sortByPriceHashMap(HashMap<Item, Integer> mapToSort) {
        List<Item> listToSort = new ArrayList<>(mapToSort.keySet());
        listToSort.sort(Comparator.comparing(Item::getDiscount).thenComparing(Item::getPrice).reversed());
        return listToSort;
    }

    private void getItemsDiscount() {

        int countDiscount = 0;
        String discountCode = "";

        sortByPrice(itemsEligibleDiscount);

        for (int i = 1; i < itemsEligibleDiscount.size() + 1; i++) {
            Item discountedItem = itemsEligibleDiscount.get(i - 1);
            if (i == 1) {
                discountCode = discountedItem.getDiscount();
            }

            if (discountCode.equals(discountedItem.getDiscount())) {
                countDiscount = countDiscount + 1;
            } else {
                countDiscount = 1;
                discountCode = discountedItem.getDiscount();
            }

            if (countDiscount % 3 == 0) {
                discountedItemsQuantity.merge(discountedItem, 1, (a, b) -> a + b);
                priceChange = priceChange + discountedItem.getPrice();
            }
        }

    }

    public int getItemsEligibleDiscountCount() {
        return itemsEligibleDiscount.size();
    }

    public void printOrder(Item item, HashMap<Item, Integer> itemsQuantity) {
        System.out.println(getQuantity(item, itemsQuantity) + ", " + item.getName() + " x " + item.getPrice());
    }

    public int getQuantity(Item item, HashMap<Item, Integer> itemsQuantity) {
        return itemsQuantity.get(item);
    }

    private void sortByPrice(List<Item> items) {
        items.sort(Comparator.comparing(Item::getDiscount).thenComparing(Item::getPrice).reversed());
    }

    @Override
    public void print() {
        //sortByPrice(getList());
        //getList().forEach(Item::printOrder);
        sortByPriceHashMap(itemsQuantity).forEach(item -> printOrder(item, itemsQuantity));
        getItemsDiscount();
        System.out.println("Total price is: " + String.format("%.02f", getPrice() - priceChange));
        StringBuilder discountedStringBuilder = new StringBuilder();
        discountedItemsQuantity.forEach((discountedItem, discountedQuantity) -> discountedStringBuilder
                        .append(discountedQuantity).append(" ").append(discountedItem.getName()).append(" "));
        /*
        for (Item discountedItem : itemsDiscount) {
            discountedStringBuilder.append(discountedItem.getDiscountedQuantity()).append(" ").append(discountedItem.getName()).append(" ");
        }
        */
        if (discountedStringBuilder.length() > 0) {
            System.out.println("You got for free: " + discountedStringBuilder);
        }
    }

}
