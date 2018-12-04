package pizzabot;

import java.util.*;

public class Order {

    private final static Comparator<Item> itemComparator = Comparator.comparing(Item::getDiscount)
            .thenComparing(Item::getPrice)
            .reversed();

    private float price = 0;
    private List<Item> itemsEligibleDiscount = new ArrayList<>();
    private List<DiscountRules> discountRulesList = new ArrayList<>();
    private DiscountCard discountCard;
    private Map<Item, Integer> itemsQuantity = new TreeMap<>(itemComparator);
    private Map<Item, Integer> discountedItemsQuantity = new HashMap<>();


    private DiscountCalculator discountCalculator;
    private float priceChange = 0;

    public Order(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public void add(Item item) {
        itemsQuantity.merge(item, 1, (a, b) -> a + b);
        if (discountCalculator.contains(item.getDiscount())) {
            itemsEligibleDiscount.add(item);
        }
        price = price + item.getPrice();
    }

    private void calcItemsDiscount() {
        int countDiscount = 0;
        String discountCode = itemsEligibleDiscount.size() > 0 ? itemsEligibleDiscount.get(0).getDiscount() : "";

        itemsEligibleDiscount.sort(itemComparator);

        for (Item discountedItem : itemsEligibleDiscount) {
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

    public void print() {
        itemsQuantity.forEach((item, q) -> System.out.println(q + ", " + item.getName() + " x " + item.getPrice()));
        calcItemsDiscount();
        System.out.println("Total price is: " + String.format("%.02f", price - priceChange));
        StringBuilder discountedStringBuilder = new StringBuilder();
        discountedItemsQuantity.forEach((item, q) ->
                discountedStringBuilder.append(q).append(" ").append(item.getName()).append(" "));
        System.out.println("You got for free: " + discountedStringBuilder);
    }

}
