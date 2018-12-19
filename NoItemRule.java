package pizzabot;

public class NoItemRule extends DiscountRule {

    private String messageToClient;

    private String discount;

    public NoItemRule(State state, boolean isActive, boolean isReusable, String discount, String messageToClient) {
        this.messageToClient = messageToClient;
        this.discount = discount;
        super.state = state;
        super.isActive = isActive;
        super.isReusable = isReusable;
    }

    @Override
    public boolean checkRule(Order order) {
        if (isApplicable()) {

            int discountedItemQuantity = 0;
            for (Item item : order.getItemsList()) {
                if (item.getDiscount().equals(discount)) {
                    discountedItemQuantity++;
                }
            }

            if (discountedItemQuantity == 0) {
                System.out.println(messageToClient + " (y/n)");
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Order order) {

    }

    @Override
    public void printRule() {

    }

}
