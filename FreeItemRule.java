package pizzabot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeItemRule extends DiscountRule {

    private String messageToClient;
    private int numberOfFreeItems;
    private float priceChangeCurrent = 0;
    private String discount;
    private List<Item> itemsEligibleDiscount = new ArrayList<>();
    private Map<Item, Integer> discountedItemsQuantity = new HashMap<>();

    public FreeItemRule(State state, boolean isActive, boolean isReusable, int numberOfFreeItems, String discount, String messageToClient) {
        this.messageToClient = messageToClient;
        this.numberOfFreeItems = numberOfFreeItems;
        this.discount = discount;

        super.state = state;
        super.isActive = isActive;
        super.isReusable = isReusable;
    }

    @Override
    public boolean checkRule(Order order) {

        if (isApplicable()){

            itemsEligibleDiscount.clear();
            for (Item item : order.getItemsList()) {
                if (item.getDiscount().equals(discount)) {
                    itemsEligibleDiscount.add(item);
                }
            }

            if ((itemsEligibleDiscount.size() % numberOfFreeItems == (numberOfFreeItems - 1))) {
                System.out.println("You currently ordered: " + itemsEligibleDiscount.size() + " " + messageToClient);
                System.out.println("Do you want to buy one more to get one for free? (y/n)");
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Order order) {
        if (isActive && needToExecute) {
            int countDiscount = 0;
            float priceChange = 0;
            discountedItemsQuantity.clear();
            for (Item discountedItem : itemsEligibleDiscount) {

                countDiscount += 1;

                if (countDiscount % numberOfFreeItems == 0) {
                    discountedItemsQuantity.merge(discountedItem, 1, (a, b) -> a + b);
                    priceChange = priceChange + discountedItem.getPrice();
                }
            }
            if (priceChange != priceChangeCurrent) {
                order.setPrice(order.getPrice() - priceChange + priceChangeCurrent);
                priceChangeCurrent = priceChange;
            }
        }
    }

    @Override
    public void printRule() {
        if (isActive && needToExecute) {
            StringBuilder discountedStringBuilder = new StringBuilder();
            discountedItemsQuantity.forEach((item, q) ->
                    discountedStringBuilder.append(q).append(" ").append(item.getName()).append(" "));
            System.out.println("You got for free: " + discountedStringBuilder);
        }
    }
}
