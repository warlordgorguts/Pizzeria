package pizzabot;

public class FreeDeliveryRule extends DiscountRule {

    private float threshold;
    private float deliveryCost;
    private float deliveryCostCurrent;
    private String messageToClient;

    public FreeDeliveryRule(State state, boolean isActive, boolean isReusable, float threshold, float deliveryCost) {
        super.state = state;
        super.isActive = isActive;
        super.isReusable = isReusable;
        this.threshold = threshold;
        this.deliveryCost = deliveryCost;
    }

    @Override
    public boolean checkRule(Order order) {
        if (isApplicable()){

            float orderPrice = order.getPrice();

            if (((threshold - orderPrice) > 0) && ((threshold - orderPrice) <= deliveryCost)) {
                System.out.println
                        ("Your order cost is: " + orderPrice + " Delivery will cost additional " + deliveryCost);
                System.out.println
                        ("Do you want to buy something to get free delivery? (start after " + threshold + ") (y/n)");
                return true;
            }
        }
        return false;
    }

    @Override
    public void execute(Order order) {
        if (isActive && needToExecute) {
            float priceChange = (order.getPrice() - deliveryCostCurrent);
            if (order.getPrice() >= threshold) {
                deliveryCostCurrent = 0;
            } else {
                deliveryCostCurrent = deliveryCost;
            }
            order.setPrice(priceChange + deliveryCostCurrent);
        }
    }

    @Override
    public void printRule() {
        if (isActive && needToExecute) {
            System.out.println("Delivery: " + deliveryCost);
        }
    }

}
