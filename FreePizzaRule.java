package pizzabot;

import java.util.*;

public class FreePizzaRule implements DiscountRules {

    private String messageToClient;
    private int priority;
    private int numberOfFreePizza;
    private String discount = "Pizza";
    private boolean isActive;
    private boolean isReusable;
    private boolean needToReuse;
    private State state;
    private List<Item> itemsEligibleDiscount = new ArrayList<>();
    private Map<Item, Integer> discountedItemsQuantity = new HashMap<>();

    public FreePizzaRule(State state, boolean isActive, boolean isReusable, String messageToClient, int priority, int numberOfFreePizza) {
        this.messageToClient = messageToClient;
        this.priority = priority;
        this.numberOfFreePizza = numberOfFreePizza;
        this.state = state;
        this.isActive = isActive;
        this.isReusable = isReusable;
    }

    @Override
    public void checkRule(Order order, Validator validator) {
        if ((!state.getValue() && isActive) || (state.getValue() && isActive && needToReuse)) {
            state.setValue(true);

            if (isReusable){
                needToReuse = true;
            }

            int countDiscount = 0;
            float priceChange = 0;

            itemsEligibleDiscount.clear();
            for (Item discountedItem : itemsEligibleDiscount) {
                if (discount.equals(discountedItem.getDiscount())) {
                    countDiscount = countDiscount + 1;
                }

                if (countDiscount % numberOfFreePizza == 0) {
                    discountedItemsQuantity.merge(discountedItem, 1, (a, b) -> a + b);
                    priceChange = priceChange + discountedItem.getPrice();
                }
            }
            //itemsEligibleDiscount.sort(itemComparator);

            if ((itemsEligibleDiscount.size() % numberOfFreePizza == (numberOfFreePizza - 1))) {
                boolean awaitingAnswer = true;
                System.out.println("You currently ordered: " + itemsEligibleDiscount.size() + " discounted items.");
                System.out.println("Do you want to buy one more to get one for free? (y/n)");
                while (awaitingAnswer) {
                    switch (new Reader().readString()) {
                        case "n": {
                            awaitingAnswer = false;
                            break;
                        }
                        case "y": {
                            awaitingAnswer = false;
                            System.out.println("Type product command:");
                            state.setValue(false);
                            validator.validate(order);
                            break;
                        }
                        default: {
                            System.out.println("Type one of the following (y/n)");
                        }
                    }
                }

            }
        }
    }

    @Override
    public void executeRule(Order order) {

    }

    @Override
    public void printRule() {
        StringBuilder discountedStringBuilder = new StringBuilder();
        discountedItemsQuantity.forEach((item, q) ->
                discountedStringBuilder.append(q).append(" ").append(item.getName()).append(" "));
        System.out.println("You got for free: " + discountedStringBuilder);
    }
}
